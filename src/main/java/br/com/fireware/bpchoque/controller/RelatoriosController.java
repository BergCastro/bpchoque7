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

import br.com.fireware.bpchoque.model.def.TesteFisico;

import br.com.fireware.bpchoque.service.def.ResultadoTafGeralService;

import br.com.fireware.bpchoque.service.def.TesteFisicoService;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {

	@Autowired
	private ResultadoTafGeralService resultadoTafGeralService;

	@Autowired
	private TesteFisicoService testeFisicoService;

	@GetMapping("/testes/{id}")
	public ModelAndView testes(@PathVariable("id") TesteFisico teste) {
		Map<String, Object> parametros = new HashMap<>();

		parametros.put("format", "pdf");
		parametros.put("teste", teste.getId());

		return new ModelAndView("relatorio_teste", parametros);
	}

	@GetMapping("/testesPorTipo/{idTeste}/{idTipo}")
	public ModelAndView testePorTipo(@PathVariable("idTeste") Long idTeste, @PathVariable("idTipo") Integer idTipo) {
		Map<String, Object> parametros = new HashMap<>();

		TesteFisico teste = testeFisicoService.findById(idTeste);

		ModelAndView mv = new ModelAndView();

		parametros.put("format", "pdf");
		parametros.put("teste", idTeste);
		parametros.put("tipo", idTipo);
		parametros.put("notaAprovacao", teste.getNotaAprovacao());
		parametros.put("objetivo", teste.getObjetivo());
		if (idTipo == 1) {
			parametros.put("nomeTipo", "TAF GERAL");
			mv.setViewName("relatorio_teste_tipo_5_provas");

		} else if (idTipo == 2) {
			parametros.put("nomeTipo", "THE CDC");
			mv.setViewName("relatorio_teste_tipo_5_provas");
		}
		mv.addObject(parametros);

		return mv;

	}

	@GetMapping("/testeGeral/{idTeste}")
	public ModelAndView testeGeral(@PathVariable("idTeste") Long idTeste) {
		Map<String, Object> parametros = new HashMap<>();

		TesteFisico teste = testeFisicoService.findById(idTeste);

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
