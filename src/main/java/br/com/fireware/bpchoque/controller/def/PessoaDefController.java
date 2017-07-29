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

import br.com.fireware.bpchoque.model.Cargo;

import br.com.fireware.bpchoque.model.OpmOrgao;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.PessoaDef.EstadoCivil;
import br.com.fireware.bpchoque.model.def.PessoaDef.Etnia;
import br.com.fireware.bpchoque.security.UsuarioSistema;
import br.com.fireware.bpchoque.model.TipoPessoa;
import br.com.fireware.bpchoque.model.Sexo;

import br.com.fireware.bpchoque.service.CargoService;

import br.com.fireware.bpchoque.service.OpmOrgaoService;
import br.com.fireware.bpchoque.service.PessoaService;
import br.com.fireware.bpchoque.service.def.PessoaDefService;

@Controller
@RequestMapping("/pessoasdef")
public class PessoaDefController {

	private static final String CADASTRO_PESSOA = "pessoasdef/CadastroPessoa";
	private static final String PESSOAS = "pessoasdef/PesquisaPessoas";

	@Autowired
	private PessoaDefService pessoaDefService;
	@Autowired
	private CargoService cargoService;
	@Autowired
	private OpmOrgaoService opmOrgaoService;

	private Boolean editando = false;
	private Boolean listaPessoasModificada = false;
	
	private List<PessoaDef> listaPessoas = new ArrayList<PessoaDef>(); 
	
	
	@RequestMapping
	public ModelAndView pessoas() {
		if(listaPessoasModificada || listaPessoas.isEmpty()){
			listaPessoas = pessoaDefService.findAll();
			listaPessoasModificada = false;
		}
		
		ModelAndView mv = new ModelAndView(PESSOAS);
		
		mv.addObject("pessoas", listaPessoas);

		return mv;
	}

	@RequestMapping("/lista")
	public List<PessoaDef> listaPessoas() {
		List<PessoaDef> todosPessoas = pessoaDefService.findAll();

		return todosPessoas;
	}

	@RequestMapping("/novo")
	public ModelAndView novo(@AuthenticationPrincipal UsuarioSistema usuarioSistema) {

		ModelAndView mv = new ModelAndView(CADASTRO_PESSOA);
		PessoaDef pessoaDef = new PessoaDef();
		editando = false;

		mv.addObject(pessoaDef);
		return mv;

	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated PessoaDef pessoa, Errors errors, RedirectAttributes attributes,
			@AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		if (errors.hasErrors()) {

			return CADASTRO_PESSOA;
		}
		if (!editando) {
			PessoaDef pessoaIncluir = pessoa;
			List<PessoaDef> pessoasCadastradas = pessoaDefService.findAll();
			for (int i = 0; i < pessoasCadastradas.size(); i++) {
				if (pessoasCadastradas.get(i).getMatricula().equals(pessoaIncluir.getMatricula())) {

					errors.rejectValue("matricula", null, "Matrícula já cadastrada!");
					return CADASTRO_PESSOA;
				}

			}
		}

		/*
		 * Locale BRAZIL = new Locale("pt", "BR"); LocalDate dataNascimento =
		 * LocalDate.parse(data.toString(),
		 * DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(BRAZIL));
		 * pessoa.setDatanasc(dataNascimento);
		 */

		pessoa.setNome(pessoa.getNome().toUpperCase());
		pessoa.setNome_guerra(pessoa.getNome_guerra().toUpperCase());

		pessoa.setEmail(pessoa.getEmail().toLowerCase());
		pessoa.setAtualizadoem(LocalDateTime.now());
		pessoa.setAtualizadopor(usuarioSistema.getUsername());

		if (pessoa.getCriadopor() == null || pessoa.getCriadopor().equals("")) {
			pessoa.setCriadoem(LocalDateTime.now());
			pessoa.setCriadopor(usuarioSistema.getUsername());
		}

		try {
			pessoaDefService.save(pessoa);
			listaPessoasModificada = true;
			attributes.addFlashAttribute("mensagem", "Pessoa salva com sucesso!");
			return "redirect:/pessoasdef";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_PESSOA;
		}
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") PessoaDef pessoa) {
		editando = true;
		ModelAndView mv = new ModelAndView();

		System.out.println("entrou em edição");
		mv.setViewName(CADASTRO_PESSOA);

		mv.addObject("pessoaDef", pessoa);
		System.out.println(pessoa.toString());
		return mv;
	}

	@RequestMapping(value = "/delete/{id}")
	public String excluir(@PathVariable Long id) {
		pessoaDefService.delete(id);
		System.out.println("Entrou no delete");

		return "redirect:/pessoasdef";
	}

	@ModelAttribute("sexos")
	public List<Sexo> sexos() {
		return Arrays.asList(Sexo.values());
	}

	@ModelAttribute("estadosCivis")
	public List<EstadoCivil> estadosCivis() {
		return Arrays.asList(EstadoCivil.values());
	}

	@ModelAttribute("tiposPessoa")
	public List<TipoPessoa> tiposPessoa() {
		return Arrays.asList(TipoPessoa.values());
	}

	@ModelAttribute("cargos")
	public Iterable<Cargo> cargos() {
		return cargoService.findAll();
	}

	@ModelAttribute("opms")
	public Iterable<OpmOrgao> opms() {
		return opmOrgaoService.findByTipoMilitar();
	}

	@ModelAttribute("orgaos")
	public Iterable<OpmOrgao> orgaos() {
		return opmOrgaoService.findByTipoCivil();
	}

	@ModelAttribute("etnias")
	public List<Etnia> etnias() {
		return Arrays.asList(Etnia.values());
	}

}