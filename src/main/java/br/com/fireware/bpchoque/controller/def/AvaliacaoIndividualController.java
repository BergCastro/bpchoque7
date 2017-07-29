package br.com.fireware.bpchoque.controller.def;



import java.time.LocalDate;
import java.util.Arrays;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.com.fireware.bpchoque.model.def.PessoaDef;

import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual;
import br.com.fireware.bpchoque.model.def.Avaliador;
import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual.Duracao;
import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual.Frequencia;
import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual.Objetivos;
import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual.Problemas;

import br.com.fireware.bpchoque.model.def.MedicaoAvaliacaoIndividual;
import br.com.fireware.bpchoque.service.def.PessoaDefService;
import br.com.fireware.bpchoque.service.def.AvaliacaoIndividualService;
import br.com.fireware.bpchoque.service.def.AvaliadorService;
import br.com.fireware.bpchoque.service.def.MedicaoAvaliacaoIndividualService;

@Controller
@RequestMapping("/avaliacoesIndividuais")
public class AvaliacaoIndividualController {
	
	private static final String CADASTRO_AVALIACAO = "avaliacaoIndividual/CadastroAvaliacaoIndividual";
	
	
	@Autowired
	private AvaliacaoIndividualService avaliacaoIndividualService;
	
	@Autowired
	private MedicaoAvaliacaoIndividualService medicaoAvaliacaoIndividualService;
	
	@Autowired
	private PessoaDefService pessoaDefService;
	
	@Autowired
	private AvaliadorService avaliadorService;
	
	@RequestMapping
	public ModelAndView avaliacaoIndividuais() {
		Iterable<AvaliacaoIndividual> todosAvaliacaoIndividuais = avaliacaoIndividualService.findAll();
		ModelAndView mv = new ModelAndView("avaliacaoIndividual/PesquisaAvaliacaoIndividual");
		mv.addObject("avaliacoes", todosAvaliacaoIndividuais);
		
		return mv;
	}
	
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		
		ModelAndView mv = new ModelAndView(CADASTRO_AVALIACAO);
		AvaliacaoIndividual avaliacaoIndividual = new AvaliacaoIndividual();
		avaliacaoIndividual.setDataAvaliacao(LocalDate.now());
		mv.addObject(avaliacaoIndividual);
		return mv;

		
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated AvaliacaoIndividual avaliacaoIndividual, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_AVALIACAO;
		}
		
		
		try {
			avaliacaoIndividualService.save(avaliacaoIndividual);
			attributes.addFlashAttribute("mensagem", "Avaliacao Individual salva com sucesso!");
			return "redirect:/avaliacoesIndividuais";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_AVALIACAO;
		}
	}
	
	@RequestMapping(value="/medicoes", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid MedicaoAvaliacaoIndividual medicao, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		medicaoAvaliacaoIndividualService.save(medicao);
		return ResponseEntity.ok(medicao);
	}
	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") AvaliacaoIndividual avaliacaoIndividual) {
		ModelAndView mv = new ModelAndView(CADASTRO_AVALIACAO); 
		mv.addObject(avaliacaoIndividual);
		Iterable<MedicaoAvaliacaoIndividual> medicoes = medicaoAvaliacaoIndividualService.findByAvaliacaoindividual(avaliacaoIndividual);
		mv.addObject(medicoes);
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		avaliacaoIndividualService.delete(id);
		System.out.println("Entrou no delete");
		
		
		return "redirect:/avaliacoesIndividuais";
	}
	
	@ModelAttribute("frequencias")
	public List<Frequencia> frequencias() {
		return Arrays.asList(Frequencia.values());
	}
	
	@ModelAttribute("duracoes")
	public List<Duracao> duracoes() {
		return Arrays.asList(Duracao.values());
	}
	
	@ModelAttribute("objetivos")
	public List<Objetivos> objetivos() {
		return Arrays.asList(Objetivos.values());
	}
	
	@ModelAttribute("problemas")
	public List<Problemas> problemas() {
		return Arrays.asList(Problemas.values());
	}
	
	@ModelAttribute("pessoas")
	public Iterable<PessoaDef> pessoas() {
		return pessoaDefService.findAll();
	}
	
	@ModelAttribute("avaliadores")
	public Iterable<Avaliador> avaliadores() {
		return avaliadorService.findAll();
	}
	
}