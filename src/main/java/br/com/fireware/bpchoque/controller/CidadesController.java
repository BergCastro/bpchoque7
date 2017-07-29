package br.com.fireware.bpchoque.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fireware.bpchoque.controller.page.PageWrapper;
import br.com.fireware.bpchoque.model.Cidade;
import br.com.fireware.bpchoque.repository.Cidades;
import br.com.fireware.bpchoque.repository.Estados;
import br.com.fireware.bpchoque.repository.filter.CidadeFilter;
import br.com.fireware.bpchoque.security.UsuarioSistema;
import br.com.fireware.bpchoque.service.CadastroCidadeService;
import br.com.fireware.bpchoque.service.exception.NomeCidadeJaCadastradaException;

@Controller
@RequestMapping("/cidades")
public class CidadesController {

	@Autowired
	private Cidades cidades;
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	private static final String CADASTRO_VIEW = "cidades/CadastroCidade";
	private static final String CIDADES = "cidades/PesquisaCidades";
	
	@RequestMapping("/novo")
	public ModelAndView nova(Cidade cidade) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("estados", estados.findAll());
		return mv;
	}
	
	@Cacheable(value = "cidades", key = "#idEstado")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long idEstado) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {	}
		return cidades.findByEstadoId(idEstado);
	}
	
	@PostMapping("/nova")
	@CacheEvict(value = "cidades", key = "#cidade.estado.id", condition = "#cidade.temEstado()")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		if (result.hasErrors()) {
			return nova(cidade);
		}
		cidade.setAtualizadoem(LocalDateTime.now());
		cidade.setAtualizadopor(usuarioSistema.getUsername());
		
		if(cidade.getCriadopor()==null || cidade.getCriadopor().equals("")){
			cidade.setCriadoem(LocalDateTime.now());
			cidade.setCriadopor(usuarioSistema.getUsername());
		}
		
		try {
			cadastroCidadeService.salvar(cidade);
		} catch (NomeCidadeJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(cidade);
		}
		
		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
		return new ModelAndView("redirect:/cidades");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CidadeFilter cidadeFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(CIDADES);
		mv.addObject("estados", estados.findAll());
		
		PageWrapper<Cidade> paginaWrapper = new PageWrapper<>(cidades.filtrar(cidadeFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
}
