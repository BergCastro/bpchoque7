package br.com.fireware.bpchoque.controller.def;

import java.time.LocalDateTime;
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

import br.com.fireware.bpchoque.security.UsuarioSistema;
import br.com.fireware.bpchoque.model.def.Avaliador;
import br.com.fireware.bpchoque.model.def.Avaliador.Escolaridade;

import br.com.fireware.bpchoque.model.def.PessoaDef;

import br.com.fireware.bpchoque.service.def.AvaliadorService;
import br.com.fireware.bpchoque.service.def.PessoaDefService;

@Controller
@RequestMapping("/comissao")
public class ComissaoController {

	private static final String CADASTRO_MEMBRO = "/CadastroMembro";
	private static final String PESQUISA_COMISSAO = "avaliadores/PesquisaMembro";

	@Autowired
	private AvaliadorService avaliadorService;

	@Autowired
	private PessoaDefService pessoaDefService;

	@RequestMapping
	public ModelAndView avaliadores() {
		Iterable<Avaliador> todosMembros = avaliadorService.findAll();
		ModelAndView mv = new ModelAndView(PESQUISA_COMISSAO);
		mv.addObject("membros", todosMembros);

		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView();

		mv.setViewName(CADASTRO_MEMBRO);
		Avaliador membro = new Avaliador();
		mv.addObject("membro", membro);
		
		return mv;

	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Avaliador membro, Errors errors, RedirectAttributes attributes,
			@AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		if (errors.hasErrors()) {
			return CADASTRO_MEMBRO;
		}
		membro.setAtualizadoem(LocalDateTime.now());
		membro.setAtualizadopor(usuarioSistema.getUsername());

		if (membro.getCriadopor() == null || membro.getCriadopor().equals("")) {
			membro.setCriadoem(LocalDateTime.now());
			membro.setCriadopor(usuarioSistema.getUsername());
		}

		try {
			avaliadorService.save(membro);
			attributes.addFlashAttribute("mensagem", "Avaliador salvo com sucesso!");
			return "redirect:/avaliadores";
		} catch (IllegalArgumentException e) {

			return CADASTRO_MEMBRO;
		}
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Avaliador membro) {
		ModelAndView mv = new ModelAndView(CADASTRO_MEMBRO);
		mv.addObject("membro", membro);
		
		return mv;
	}

	@RequestMapping(value = "/delete/{id}")
	public String excluir(@PathVariable Long id) {
		avaliadorService.delete(id);
		

		return "redirect:/avaliadores";
	}

	@ModelAttribute("pessoas")
	public Iterable<PessoaDef> pessoas() {
		return pessoaDefService.findAll();
	}

	@ModelAttribute("escolaridades")
	public List<Escolaridade> problemas() {
		return Arrays.asList(Escolaridade.values());
	}

}