package br.com.fireware.bpchoque.controller.def;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




import br.com.fireware.bpchoque.model.def.Doacao;
import br.com.fireware.bpchoque.model.def.Doacao.DoacaoTipo;
import br.com.fireware.bpchoque.security.UsuarioSistema;
import br.com.fireware.bpchoque.model.def.DoacaoDetalhe;
import br.com.fireware.bpchoque.service.def.DoacaoDetalheService;
import br.com.fireware.bpchoque.service.def.DoacaoService;

@Controller
@RequestMapping("/doacoes")
public class DoacaoController {
	
	private static final String CADASTRO_DOACAO = "doacoes/CadastroDoacoes";
	private static final String DOACOES = "doacoes/Doacoes";
	
	@Autowired
	private DoacaoService doacaoService;
	
	@Autowired
	private DoacaoDetalheService doacaoDetalheService;
	
	private List<DoacaoDetalhe> detalhes;
	
	private Doacao doacao;
	
	@RequestMapping
	public ModelAndView doacoes() {
		
		
		Iterable<Doacao> todosDoacoes = doacaoService.findAll();
		ModelAndView mv = new ModelAndView(DOACOES);
		mv.addObject("doacoes", todosDoacoes);
		
		
		return mv;
	}
	
	@RequestMapping("/lista")
	public List<Doacao> listaDoacoes() {
		List<Doacao> todosDoacoes = doacaoService.findAll();
		
		return todosDoacoes;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView();
		detalhes = new ArrayList<DoacaoDetalhe>();
		mv.setViewName(CADASTRO_DOACAO);
		this.doacao = new Doacao();
		doacao.setDataDoacao(LocalDate.now());
		
		mv.addObject("doacao", doacao);
		mv.addObject("tipos", DoacaoTipo.values());
		return mv;

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Doacao doacao, Errors errors, RedirectAttributes attributes,
			@AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		if (errors.hasErrors()) {
			return CADASTRO_DOACAO;
		}
		doacao.setAtualizadoem(LocalDateTime.now());
		doacao.setAtualizadopor(usuarioSistema.getUsername());

		if (doacao.getCriadopor() == null || doacao.getCriadopor().equals("")) {
			doacao.setCriadoem(LocalDateTime.now());
			doacao.setCriadopor(usuarioSistema.getUsername());
		}

		try {
			
			
			doacaoService.save(doacao);
			
			for(DoacaoDetalhe detalhe: detalhes){
				
				detalhe.setDoacao(doacao);
				
				doacaoDetalheService.save(detalhe);
			}
			attributes.addFlashAttribute("mensagem", "Doação salva com sucesso!");
			return "redirect:/doacoes/"+doacao.getId();
		} catch (IllegalArgumentException e) {

			return CADASTRO_DOACAO;
		}
	}
	@PostMapping(value="/detalheNovo", consumes={ MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvarDetalhe(@RequestBody @Valid DoacaoDetalhe detalhe , BindingResult result) {
		
		if(result.getFieldValue("tipo").equals("VAZIO")){
			return ResponseEntity.badRequest().body("Selecione um tipo de doação!");
		}else if (result.hasFieldErrors("quantidade")) {
			
			return ResponseEntity.badRequest().body(result.getFieldError("quantidade").getDefaultMessage());
		}else if(result.hasFieldErrors("descricao")){
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}
		
		detalhe.setDoacao(doacao);
		
		detalhes.add(detalhe);
		return ResponseEntity.ok(detalhe);
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Doacao doacao) {
		ModelAndView mv = new ModelAndView(CADASTRO_DOACAO);
		this.doacao = doacao;
		mv.addObject(doacao);
		List<DoacaoDetalhe> detalhesBusca = doacaoDetalheService.findByDoacao(doacao);
		detalhes = new ArrayList<DoacaoDetalhe>();
		detalhes.addAll(detalhesBusca);
		mv.addObject("detalhes", detalhes);
		mv.addObject("tipos", DoacaoTipo.values());
		return mv;
	}
	
	public List<DoacaoDetalhe> listaDetalhes(){
		return detalhes;
	}
	
	
	
	
	
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		Doacao doacao = doacaoService.findById(id);
		doacaoDetalheService.deleteByDoacao(doacao);
		doacaoService.delete(id);
		
		
		
		
		return "redirect:/doacoes";
	}
	
	@RequestMapping(value="/deleteDetalhe/{id}")
	public String excluirDetalhe(@PathVariable Long id) {
	
		if(id != null){
			doacaoDetalheService.delete(id);
		}
		
		
		
		
		return "redirect:/doacoes/"+doacao.getId();
	}
	
	
	
}