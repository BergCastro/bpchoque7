package br.com.fireware.bpchoque.controller.def;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fireware.bpchoque.model.Sexo;
import br.com.fireware.bpchoque.model.def.Prova;
import br.com.fireware.bpchoque.model.def.Prova.CampoTipo;
import br.com.fireware.bpchoque.security.UsuarioSistema;
import br.com.fireware.bpchoque.service.def.ProvaService;

@Controller
@RequestMapping("/provas")
public class ProvaController {
	
	private static final String CADASTRO_PROVA = "provas/CadastroProva";
	private static final String PESQUISA_PROVAS = "provas/Provas";
	
	@Autowired
	private ProvaService provaService;
	
	
	@RequestMapping
	public ModelAndView provas() {
		Iterable<Prova> todosProvas = provaService.findAll();
		List<Prova> provasDescricaoCurta = new ArrayList<Prova>();
		for (Prova prova : todosProvas) {
			if(prova.getDescricao().length() > 50){
				prova.setDescricao(prova.getDescricao().substring(0, 50));
			}
			provasDescricaoCurta.add(prova);
		}
		ModelAndView mv = new ModelAndView(PESQUISA_PROVAS);
		mv.addObject("provas", provasDescricaoCurta);
		
		return mv;
	}
	
	@RequestMapping("/lista")
	public List<Prova> listaProvas() {
		List<Prova> todosProvas = provaService.findAll();
		
		return todosProvas;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(@AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		
		ModelAndView mv = new ModelAndView(CADASTRO_PROVA);
		Prova prova = new Prova();
		
		
		mv.addObject("prova", prova);
		mv.addObject("tipos", CampoTipo.values());
		return mv;
			
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar( @Validated Prova prova, Errors errors, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		if (errors.hasErrors()) {
			
			return CADASTRO_PROVA;
		}
		
	
			
			prova.setAtualizadoem(LocalDateTime.now());
			prova.setAtualizadopor(usuarioSistema.getUsername());
			
			if(prova.getCriadopor()==null || prova.getCriadopor().equals("")){
				prova.setCriadoem(LocalDateTime.now());
				prova.setCriadopor(usuarioSistema.getUsername());
			}
			
		
		try {
			provaService.save(prova);
			attributes.addFlashAttribute("mensagem", "Prova salva com sucesso!");
			return "redirect:/provas";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_PROVA;
		}
	}
	
	
	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Prova prova) {
		
		ModelAndView mv = new ModelAndView();
		
			System.out.println("entrou em edição");
			mv.setViewName(CADASTRO_PROVA);
		
			
		
		mv.addObject("prova", prova);
		mv.addObject("tipos", CampoTipo.values());
		System.out.println(prova.toString());
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		provaService.delete(id);
		
		
		
		return "redirect:/provas";
	}
	
	@ModelAttribute("tipos")
	public List<CampoTipo> tipos() {
		return Arrays.asList(CampoTipo.values());
	}
	
	
}