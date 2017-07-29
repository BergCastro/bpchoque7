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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fireware.bpchoque.model.def.TipoTeste;
import br.com.fireware.bpchoque.model.def.Prova.AptoInapto;

import br.com.fireware.bpchoque.security.UsuarioSistema;

import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.Prova;
import br.com.fireware.bpchoque.model.def.Resultado;
import br.com.fireware.bpchoque.model.def.ResultadoTeste;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.service.def.AvaliadorService;
import br.com.fireware.bpchoque.service.def.ComissaoService;
import br.com.fireware.bpchoque.service.def.PessoaDefService;
import br.com.fireware.bpchoque.service.def.ProvaService;
import br.com.fireware.bpchoque.service.def.ResultadoTesteService;
import br.com.fireware.bpchoque.service.def.TesteFisicoService;
import br.com.fireware.bpchoque.service.def.TipoTesteService;

@Controller
@RequestMapping("/testesFisicos")
public class TesteFisicoController {

	private static final String CADASTRO_TESTE_FISICO = "testesFisicos/CadastroTesteFisico";
	private static final String EDITAR_RESULTADO = "testesFisicos/EditaResultadoSemModal";
	

	@Autowired
	private TesteFisicoService testeFisicoService;

	@Autowired
	private TipoTesteService tipoTesteService;

	@Autowired
	private ResultadoTesteService resultadoTesteService;

	@Autowired
	private PessoaDefService pessoaDefService;

	@Autowired
	private ProvaService provaService;
	
	@Autowired
	private AvaliadorService avaliadorService;
	
	@Autowired
	private ComissaoService comissaoService;

	private List<TipoTeste> tiposTestes;

	private TesteFisico testeFisico;

	private boolean testeFisicoSalvo = false;

	private boolean temTipo = false;

	@RequestMapping
	public ModelAndView tiposTeste() {

		Iterable<TesteFisico> todosTestes = testeFisicoService.findAll();
		
		ModelAndView mv = new ModelAndView("testesFisicos/TestesFisicos");
		mv.addObject("testes", todosTestes);
		

		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView();
		
		List<TipoTeste> todosTipos = tipoTesteService.findAll();
		tiposTestes = new ArrayList<TipoTeste>();
		mv.setViewName(CADASTRO_TESTE_FISICO);
		this.testeFisico = new TesteFisico();
		testeFisico.setData(LocalDate.now());
		testeFisicoSalvo = false;
		mv.addObject("testeFisico", testeFisico);
		mv.addObject("provasIncluir", todosTipos);
		mv.addObject("testeFisicoSalvo", testeFisicoSalvo);
		return mv;

	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated TesteFisico testeFisico, Errors errors, RedirectAttributes attributes,
			@AuthenticationPrincipal UsuarioSistema usuarioSistema) {

		if (errors.hasErrors()) {
			return CADASTRO_TESTE_FISICO;
		}
		testeFisico.setAtualizadoem(LocalDateTime.now());
		testeFisico.setAtualizadopor(usuarioSistema.getUsername());

		if (testeFisico.getCriadopor() == null || testeFisico.getCriadopor().equals("")) {
			testeFisico.setCriadoem(LocalDateTime.now());
			testeFisico.setCriadopor(usuarioSistema.getUsername());
		}

		try {

			testeFisicoService.save(testeFisico);
			this.testeFisico = testeFisico;
			testeFisicoSalvo = true;
			attributes.addFlashAttribute("mensagem", "Tipo salvo com sucesso!");
			return "redirect:/testesFisicos/" + testeFisico.getId();
		} catch (IllegalArgumentException e) {

			return CADASTRO_TESTE_FISICO;
		}
	}
	
	@RequestMapping("/resultados")
	public String ajaxBrands(Model model) {
		List<ResultadoTeste> resultados = resultadoTesteService.findByTeste(testeFisico);
		System.out.println("Entrou no ajaxResultados");
		model.addAttribute("resultados", resultados);
		model.addAttribute("tipos", testeFisico.getTipos());
		model.addAttribute("pessoasIncluir", testeFisicoService.pessoasIncluir(resultados));
		return "testesFisicos/CadastroTesteFisico :: resultadosFrag";
	}
	
	@GetMapping("/atualizaEditaResultado/{id}")
	public String atualizaEditaResultado(Model model, @PathVariable Long id) {
		System.out.println("Entrou no atualiza edita resultado");
		System.out.println("teste"+id);
		ResultadoTeste resultado = resultadoTesteService.findById(id);
		Prova prova1 = null;
		Prova prova2 = null;
		Prova prova3 = null;
		Prova prova4 = null;
		Prova prova5 = null;
		Prova prova6 = null;
		List<String> valores = new ArrayList<String>();
		valores.add(resultado.getValorProva1());
		
		if(resultado.getProva1() != null){
			prova1 = provaService.findById(resultado.getProva1());
			model.addAttribute("nomeProva1", prova1.getNome());
			model.addAttribute("valorProva1", resultado.getValorProva1());
		}else{
			model.addAttribute("nomeProva1", "");
			model.addAttribute("valorProva1", "");
		}
		if(resultado.getProva2() != null){
			prova2 = provaService.findById(resultado.getProva2());
			model.addAttribute("nomeProva2", prova2.getNome());
			model.addAttribute("valorProva2", resultado.getValorProva2());
		}else{
			model.addAttribute("nomeProva2", "");
			model.addAttribute("valorProva2", "");
		}
		if(resultado.getProva3() != null){
			prova3 = provaService.findById(resultado.getProva3());
			model.addAttribute("nomeProva3", prova3.getNome());
			model.addAttribute("valorProva3", resultado.getValorProva3());
		}else{
			model.addAttribute("nomeProva3", "");
			model.addAttribute("valorProva3", "");
		}
		if(resultado.getProva4() != null){
			prova4 = provaService.findById(resultado.getProva4());
			model.addAttribute("nomeProva4", prova4.getNome());
			model.addAttribute("valorProva4", resultado.getValorProva4());
		}else{
			model.addAttribute("nomeProva4", "");
			model.addAttribute("valorProva4", "");
		}
		if(resultado.getProva5() != null){
			prova5 = provaService.findById(resultado.getProva5());
			model.addAttribute("nomeProva5", prova5.getNome());
			model.addAttribute("valorProva5", resultado.getValorProva5());
		}else{
			model.addAttribute("nomeProva5", "");
			model.addAttribute("valorProva5", "");
		}
		if(resultado.getProva6() != null){
			prova6 = provaService.findById(resultado.getProva6());
			model.addAttribute("nomeProva6", prova6.getNome());
			model.addAttribute("valorProva6", resultado.getValorProva6());
		}else{
			model.addAttribute("nomeProva6", "");
			model.addAttribute("valorProva6", "");
		}
		
		
		
		model.addAttribute("resultado", resultado);
		
		
		
		model.addAttribute("tipos", testeFisico.getTipos());
		
		
		return "testesFisicos/EditaResultado :: corpoModalEditaFrag";
	}
	
	@RequestMapping("/atualizaJavaScript")
	public String atualizaJavaScript() {
		System.out.println("Entrou no atualiza java script");
		return "layout/LayoutPadrao :: javascriptFrag";
	}

	@RequestMapping(value = "/tipoNovo", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView salvarTipo(@RequestBody TipoTeste tipoTeste, BindingResult result,
			RedirectAttributes attributes, Errors errors, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		System.out.println("Entrou no Tipo Novo");
		ModelAndView mv = new ModelAndView();

	
		tipoTeste = tipoTesteService.findById(tipoTeste.getId());
		System.out.println("Tipo teste: "+tipoTeste);

		testeFisico.getTipos().add(tipoTeste);

		mv.addObject("tipoTeste", tipoTeste);

		

		testeFisicoService.save(testeFisico);

		attributes.addFlashAttribute("mensagem", "Teste salvo com sucesso!");
		mv.setViewName("redirect:/testesFisicos/" + testeFisico.getId());
		return mv;

	}

	@RequestMapping(value = "/resultadoNovo", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvarResultado(@RequestBody @Valid Resultado resultado, BindingResult result,
			RedirectAttributes attributes, Errors errors, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {

		ModelAndView mv = new ModelAndView();

		
		testeFisicoService.salvaResultado(resultado, testeFisico);
		

		mv.setViewName("redirect:/testesFisicos/" + testeFisico.getId());
		return ResponseEntity.ok(resultado);

	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") TesteFisico testeFisico) {
		ModelAndView mv = new ModelAndView(CADASTRO_TESTE_FISICO);
		
		this.testeFisico = testeFisico;
		mv.addObject(testeFisico);

		List<TipoTeste> tiposIncluir = tipoTesteService.findAll();

		if (testeFisico.getTipos().size() > 0) {
			
			for (int i = 0; i < tiposIncluir.size(); i++) {
				for (int j = 0; j < testeFisico.getTipos().size(); j++) {
					try {
						if (testeFisico.getTipos().get(j).getId() == tiposIncluir.get(i).getId()) {
							tiposIncluir.remove(i);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			}

		}
		
		
		
		if (testeFisico.getTipos().size() > 0) {
			temTipo = true;
		}
		testeFisicoSalvo = true;
		

		List<ResultadoTeste> resultados = resultadoTesteService.findByTeste(testeFisico);
		List<PessoaDef> pessoasIncluir = testeFisicoService.pessoasIncluir(resultados);
		
		
		
		Integer qtdProvas = 0;

		for (int i = 0; i < testeFisico.getTipos().size(); i++) {
			qtdProvas += testeFisico.getTipos().get(i).getQtdProvas();
		}

		mv.addObject("tipos", testeFisico.getTipos());
		mv.addObject("qtdProvas", qtdProvas);
		
		mv.addObject("resultados", resultados);
		mv.addObject("tiposIncluir", tiposIncluir);
		mv.addObject("pessoasIncluir", pessoasIncluir);
		mv.addObject("testeFisicoSalvo", testeFisicoSalvo);
		
		mv.addObject("resultadosBooleanos", AptoInapto.values());
		mv.addObject("temTipo", temTipo);
		mv.addObject("comissao", comissaoService.findByTesteFisico(testeFisico));

		return mv;
	}
	
	@RequestMapping("/resultado/{id}")
	public ModelAndView edicaoResultado(@PathVariable("id") ResultadoTeste resultado) {
		ModelAndView mv = new ModelAndView(CADASTRO_TESTE_FISICO);
		
		this.testeFisico = testeFisico;
		mv.addObject(testeFisico);

		List<TipoTeste> tiposIncluir = tipoTesteService.findAll();

		if (testeFisico.getTipos().size() > 0) {
			
			for (int i = 0; i < tiposIncluir.size(); i++) {
				for (int j = 0; j < testeFisico.getTipos().size(); j++) {
					if (testeFisico.getTipos().get(j).getId() == tiposIncluir.get(i).getId()) {
						tiposIncluir.remove(i);
					}
				}
			}

		}
		
		
		
		if (testeFisico.getTipos().size() > 0) {
			temTipo = true;
		}
		testeFisicoSalvo = true;
		

		List<ResultadoTeste> resultados = resultadoTesteService.findByTeste(testeFisico);
		List<PessoaDef> pessoasIncluir = testeFisicoService.pessoasIncluir(resultados);
		
		
		
		Integer qtdProvas = 0;

		for (int i = 0; i < testeFisico.getTipos().size(); i++) {
			qtdProvas += testeFisico.getTipos().get(i).getQtdProvas();
		}

		mv.addObject("tipos", testeFisico.getTipos());
		mv.addObject("qtdProvas", qtdProvas);
		mv.addObject("resultados", resultados);
		mv.addObject("tiposIncluir", tiposIncluir);
		mv.addObject("pessoasIncluir", pessoasIncluir);
		mv.addObject("testeFisicoSalvo", testeFisicoSalvo);
		mv.addObject("resultadosBooleanos", AptoInapto.values());
		mv.addObject("temTipo", temTipo);

		return mv;
	}
	
	@GetMapping("/editaResultadoTemp/{id}")
	public ModelAndView editaResultadoTemp(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView(EDITAR_RESULTADO);
		
		ResultadoTeste resultado = resultadoTesteService.findById(id);
		
		
		
		mv.addObject("resultado", resultado);
		
		
		
		return mv;
	}

	@RequestMapping(value = "/delete/{id}")
	public String excluir(@PathVariable Long id) {
		TesteFisico testeFisico = testeFisicoService.findById(id);
		
		
		List<ResultadoTeste> resultados = resultadoTesteService.findByTeste(testeFisico);
		
		
		for (int i = 0; i < resultados.size(); i++) {
			resultadoTesteService.delete(resultados.get(i));
		}
		
		testeFisicoService.delete(id);

		return "redirect:/testesFisicos";
	}

	@RequestMapping(value = "/deleteTipo/{id}")
	public ModelAndView excluirTipo(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();

		TipoTeste tipoRemover = tipoTesteService.findById(id);

		for (int i = 0; i < testeFisico.getTipos().size(); i++) {
			if (testeFisico.getTipos().get(i).getId() == tipoRemover.getId()) {
				testeFisico.getTipos().remove(i);
			}
		}

		testeFisicoService.save(testeFisico);

		mv.addObject("testeFisico", testeFisico);
		mv.setViewName("redirect:/testesFisicos/" + testeFisico.getId());
		return mv;
	}
	
	@DeleteMapping(value = "/deleteResultado/{id}")
	public String excluirResultado(@PathVariable Long id, Model model) {
		
		ResultadoTeste resultado = resultadoTesteService.findById(id);
		PessoaDef pessoa = pessoaDefService.findById(resultado.getPessoa().getId());
	
		List<ResultadoTeste> resultadosDeletar = resultadoTesteService.findByPessoa(pessoa);
		
		
		for (int i = 0; i < resultadosDeletar.size(); i++) {
			resultadoTesteService.delete(resultadosDeletar.get(i).getId());
		}
		List<ResultadoTeste> resultados = resultadoTesteService.findByTeste(testeFisico);
		model.addAttribute("resultados", resultados);
		model.addAttribute("tipos", testeFisico.getTipos());
		return "testesFisicos/CadastroTesteFisico :: resultadosFrag";
	}
	
	

}