package br.com.fireware.bpchoque.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fireware.bpchoque.dto.PeriodoRelatorio;
import br.com.fireware.bpchoque.model.def.Prova;
import br.com.fireware.bpchoque.model.def.ResultadoTeste;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.model.def.TipoTeste;
import br.com.fireware.bpchoque.service.def.ResultadoTesteService;
import br.com.fireware.bpchoque.service.def.TesteFisicoService;
import br.com.fireware.bpchoque.service.def.TipoTesteService;


@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	@Autowired
	private TipoTesteService tipoTesteService;
	
	@Autowired
	private ResultadoTesteService resultadoTesteService;
	
	@Autowired
	private TesteFisicoService testeFisicoService;
	
	@GetMapping("/vendasEmitidas")
	public ModelAndView relatorioVendasEmitidas() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioVendasEmitidas");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}
	
	@GetMapping("/testes/{id}")
	public ModelAndView testes(@PathVariable("id") TesteFisico teste) {
		Map<String, Object> parametros = new HashMap<>();
		
		
		
		parametros.put("format", "pdf");
		parametros.put("teste", teste.getId());
		
		return new ModelAndView("relatorio_teste", parametros);
	}
	
	@GetMapping("/testesPorTipo/{idTeste}/{idTipo}")
	public ModelAndView testePorTipo(@PathVariable("idTeste") Long idTeste, @PathVariable("idTipo") Long idTipo) {
		Map<String, Object> parametros = new HashMap<>();
		
		TipoTeste tipo = tipoTesteService.findById(idTipo);
		TesteFisico teste = testeFisicoService.findById(idTeste);
		
		List<String> provas = new ArrayList<>();
		for(Prova prova : tipo.getProvas()){
			provas.add(prova.getNome());
		}
		
		
		String prova1 = "";
		String prova2 = "";
		String prova3 = "";
		String prova4 = "";
		String prova5 = "";
		String prova6 = "";
		
		if(provas.size() > 0 ){
			prova1 = provas.get(0);
		}
		if(provas.size() > 1){
			prova2 = provas.get(1);
		}
		if(provas.size() > 2){
			prova3 = provas.get(2);
		}
		if(provas.size() > 3){
			prova4 = provas.get(3);
		}
		if(provas.size() > 4){
			prova5 = provas.get(4);
		}
		if(provas.size() > 5){
			prova6 = provas.get(5);
		}
		
		
		
		parametros.put("prova1", prova1);
		parametros.put("prova2", prova2);
		parametros.put("prova3", prova3);
		parametros.put("prova4", prova4);
		parametros.put("prova5", prova5);
		parametros.put("prova6", prova6);
		parametros.put("format", "pdf");
		parametros.put("teste", idTeste);
		parametros.put("tipo", idTipo);
		parametros.put("notaAprovacao", teste.getNotaAprovacao());
		parametros.put("objetivo", teste.getObjetivo());
		parametros.put("nomeTipo", tipo.getNome());
		if(tipo.getQtdProvas() == 5){
			return new ModelAndView("relatorio_teste_tipo_5_provas", parametros);
		}
		
		return new ModelAndView("relatorio_teste_tipo", parametros);
	}
	
	@GetMapping("/testeGeral/{idTeste}")
	public ModelAndView testeGeral(@PathVariable("idTeste") Long idTeste) {
		Map<String, Object> parametros = new HashMap<>();
		
		
		TesteFisico teste = testeFisicoService.findById(idTeste);
		Integer qtdTipos = teste.getTipos().size();
		List<TipoTeste> tipos = new ArrayList<TipoTeste>();
		for(TipoTeste tipo : teste.getTipos()){
			tipos.add(tipo);
		}
		
		
		String tipo1 = "";
		String tipo2 = "";
		String tipo3 = "";
		String tipo4 = "";
		
		
		Long idTipo1 = 0L;
		Long idTipo2 = 0L;
		Long idTipo3 = 0L;
		Long idTipo4 = 0L;
		
		if(tipos.size() > 0 ){
			tipo1 = tipos.get(0).getNome();
			idTipo1 = tipos.get(0).getId();
			
		}
		if(tipos.size() > 1){
			tipo2 = tipos.get(1).getNome();
			idTipo2 = tipos.get(1).getId();
		}
		if(tipos.size() > 2){
			tipo3 = tipos.get(2).getNome();
			idTipo3 = tipos.get(2).getId();
		}
		if(tipos.size() > 3){
			tipo4 = tipos.get(3).getNome();
			idTipo4 = tipos.get(3).getId();
		}
		
		
		
		
		
		parametros.put("tipo1", tipo1);
		parametros.put("tipo2", tipo2);
		parametros.put("tipo3", tipo3);
		parametros.put("tipo4", tipo4);
		parametros.put("idTipo1", idTipo1);
		parametros.put("idTipo2", idTipo2);
		parametros.put("idTipo3", idTipo3);
		parametros.put("idTipo4", idTipo4);
		parametros.put("qtdTipos", qtdTipos);
		
		parametros.put("format", "pdf");
		parametros.put("teste", idTeste);
		
		parametros.put("notaAprovacao", teste.getNotaAprovacao());
		parametros.put("objetivo", teste.getObjetivo());
		return new ModelAndView("relatorio_teste_geral", parametros);
	}
	
	@PostMapping("/vendasEmitidas")
	public ModelAndView gerarRelatorioVendasEmitidas(PeriodoRelatorio periodoRelatorio) {
		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		
		return new ModelAndView("relatorio_vendas_emitidas", parametros);
	}

}
