package br.com.fireware.bpchoque.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fireware.bpchoque.dto.VendaMes;
import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.ResultadoTeste;
import br.com.fireware.bpchoque.model.def.TesteFisico;


import br.com.fireware.bpchoque.repository.Clientes;

import br.com.fireware.bpchoque.repository.def.AvaliacaoIndividualRepository;
import br.com.fireware.bpchoque.repository.def.AvaliadorRepository;
import br.com.fireware.bpchoque.repository.def.DoacaoRepository;
import br.com.fireware.bpchoque.repository.def.PessoaDefRepository;
import br.com.fireware.bpchoque.repository.def.ResultadoTesteRepository;
import br.com.fireware.bpchoque.repository.def.TesteFisicoRepository;

@Controller
public class DashboardController {

	
	
	@Autowired
	private PessoaDefRepository pessoas;
	
	@Autowired
	private TesteFisicoRepository testeFisicoRepository;
	
	@Autowired
	private AvaliacaoIndividualRepository testesIndividuais;
	
	@Autowired
	private ResultadoTesteRepository resultadoTesteRepository;
	
	@Autowired
	private DoacaoRepository doacaoRepository;
	
	@Autowired
	private AvaliadorRepository avaliadorRepository;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		
		mv.addObject("totalPessoas", pessoas.count());
		mv.addObject("testesColetivos", testeFisicoRepository.count());
		mv.addObject("testesIndividuais", testesIndividuais.count());
		mv.addObject("pessoasAvaliadas", calculaPessoasAvaliadas());
		mv.addObject("doacoes", doacaoRepository.count());
		mv.addObject("avaliadores", avaliadorRepository.count());
		return mv;
		
		
	 
	}
	
	
	
	public Integer calculaPessoasAvaliadas(){
		Set<PessoaDef> pessoas = new HashSet<>();
		List<ResultadoTeste> resultados = resultadoTesteRepository.findAll();
		for (ResultadoTeste resultado : resultados) {
			pessoas.add(resultado.getPessoa());
		}
		
		
		return pessoas.size(); 
	}
	
	
	
}
