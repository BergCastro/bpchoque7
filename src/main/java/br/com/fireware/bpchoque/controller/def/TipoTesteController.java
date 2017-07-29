package br.com.fireware.bpchoque.controller.def;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fireware.bpchoque.model.def.TipoTeste;

import br.com.fireware.bpchoque.security.UsuarioSistema;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.Prova;
import br.com.fireware.bpchoque.model.def.ResultadoTeste;
import br.com.fireware.bpchoque.service.def.ProvaService;
import br.com.fireware.bpchoque.service.def.ResultadoTesteService;
import br.com.fireware.bpchoque.service.def.TesteFisicoService;
import br.com.fireware.bpchoque.service.def.TipoTesteService;
import br.com.fireware.bpchoque.util.RemoveColecao;

@Controller
@RequestMapping("/tiposTeste")
public class TipoTesteController {

	private static final String CADASTRO_TIPO_TESTE = "tiposTeste/CadastroTipoTeste";

	@Autowired
	private TipoTesteService tipoTesteService;

	@Autowired
	private ProvaService provaService;
	
	@Autowired
	private ResultadoTesteService resultadoService;

	private List<Prova> provas;

	private TipoTeste tipoTeste;
	
	private boolean testeFisicoSalvo = false;
	
	@RequestMapping
	public ModelAndView tiposTeste() {

		Iterable<TipoTeste> todosTipos = tipoTesteService.findAll();
		ModelAndView mv = new ModelAndView("tiposTeste/TiposTeste");
		mv.addObject("tipos", todosTipos);

		return mv;
	}

	@RequestMapping("/lista")
	public List<TipoTeste> listaDoacoes() {
		List<TipoTeste> todosDoacoes = tipoTesteService.findAll();

		return todosDoacoes;
	}

	@RequestMapping("/novo")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView();
		List<Prova> todasProvas = provaService.findAll();
		provas = new ArrayList<Prova>();
		mv.setViewName(CADASTRO_TIPO_TESTE);
		this.tipoTeste = new TipoTeste();
		testeFisicoSalvo = false;
		mv.addObject("tipoTeste", tipoTeste);
		mv.addObject("provasIncluir", todasProvas);
		mv.addObject("testeFisicoSalvo", testeFisicoSalvo);
		return mv;

	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated TipoTeste tipoTeste, Errors errors, RedirectAttributes attributes,
			@AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		
		if (errors.hasErrors()) {
			return CADASTRO_TIPO_TESTE;
		}
		tipoTeste.setAtualizadoem(LocalDateTime.now());
		tipoTeste.setAtualizadopor(usuarioSistema.getUsername());

		if (tipoTeste.getCriadopor() == null || tipoTeste.getCriadopor().equals("")) {
			tipoTeste.setCriadoem(LocalDateTime.now());
			tipoTeste.setCriadopor(usuarioSistema.getUsername());
		}
		
		try {
			
			tipoTesteService.save(tipoTeste);
			this.tipoTeste = tipoTeste;
			testeFisicoSalvo = true;
			attributes.addFlashAttribute("mensagem", "Tipo salvo com sucesso!");
			return "redirect:/tiposTeste/" + tipoTeste.getId();
		} catch (IllegalArgumentException e) {

			return CADASTRO_TIPO_TESTE;
		}
	}

	@RequestMapping(value = "/provaNovo", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvarDetalhe(@RequestBody @Valid Prova prova, BindingResult result, RedirectAttributes attributes,
			Errors errors, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		
		
		
		// detalhe.setTipoTeste(tipoTeste);
		Set<Prova> provas = new HashSet<Prova>();
		Prova provaNova = provaService.findById(prova.getId());
		provas.addAll(tipoTeste.getProvas());
		provas.add(provaNova);
		
		if(tipoTeste.getProvas().size() < 6){
			
			try{
			tipoTeste.setProvas(provas);
			
			tipoTesteService.save(tipoTeste);
			

			
			}catch (Exception e) {
			
			}
		}else{
			return ResponseEntity.badRequest().body("O número máximo de provas por tipo é 6!");
		}
		attributes.addFlashAttribute("provasIncluir", atualizarProvasIncluir());
		
			return ResponseEntity.ok(prova);
		
		
	}

	
	@RequestMapping("/atualizaProvas")
	public String ajaxBrands2(Model model) {
		Set<Prova> provas = tipoTeste.getProvas();
		
		model.addAttribute("provas", provas);
		
		return "tiposTeste/CadastroTipoTeste :: provasFragment";
	}
	@RequestMapping("/atualizaProvas2")
	public ModelAndView ajaxBrands(Model model) {
		ModelAndView mv = new ModelAndView("tiposTeste/CadastroProvaTipo");
		Set<Prova> provas = tipoTeste.getProvas();
		
		model.addAttribute("provas", provas);
		mv.addObject("provas", provas);
		return mv;
	}
	
	@RequestMapping("/atualizaModal")
	public String atualizaModal(Model model) {
		System.out.println("entrou no atualiza modal");
		model.addAttribute("provasIncluir", atualizarProvasIncluir());
		return "tiposTeste/CadastroTipoTeste :: modalFragment";
	}
	

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") TipoTeste tipoTeste) {
		ModelAndView mv = new ModelAndView(CADASTRO_TIPO_TESTE);
		this.tipoTeste = tipoTeste;
		
		mv.addObject(tipoTeste);
						
		testeFisicoSalvo = true;
				
		mv.addObject("provasIncluir", atualizarProvasIncluir());
		mv.addObject("testeFisicoSalvo", testeFisicoSalvo);
		mv.addObject("provas", tipoTeste.getProvas());
		
		return mv;
	}

	

	public List<Prova> listaDetalhes() {
		return provas;
	}

	@RequestMapping(value = "/delete/{id}")
	public String excluir(@PathVariable Long id) {
		TipoTeste tipoTeste = tipoTesteService.findById(id);
		
		tipoTesteService.delete(id);

		return "redirect:/tiposTeste";
	}

	@DeleteMapping(value = "/deleteProva/{id}")
	public @ResponseBody ResponseEntity<?> excluirProva(@PathVariable Long id) {
		
		
		System.out.println("Entrou no deleteProva");
		Prova provaRemover = provaService.findById(id); 
		
		tipoTeste.getProvas().remove(provaRemover);
		
		tipoTesteService.save(tipoTeste);
		System.out.println("Entrou no deleteProva e deletou");
		
		
		return ResponseEntity.ok(tipoTeste);
	}
	
	private List<Prova> atualizarProvasIncluir() {
		List<Prova> provasIncluir = provaService.findAll();
		Set<Prova> remover = tipoTeste.getProvas();
		RemoveColecao.removeOfThis(provasIncluir, remover);
		return provasIncluir;
	}
	
	

}