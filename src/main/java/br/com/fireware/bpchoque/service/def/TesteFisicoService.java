package br.com.fireware.bpchoque.service.def;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.ResultadoTafGeral;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.repository.def.TesteFisicoRepository;
import br.com.fireware.bpchoque.util.RemoveColecao;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TesteFisicoService {

	@Autowired
	private TesteFisicoRepository repository;

	@Autowired
	private PessoaDefService pessoaDefService;

	@Autowired
	private ResultadoTafGeralService resultadoTafGeralService;

	@Autowired
	private ProvaService provaService;

	@Transactional(readOnly = false)
	public void save(TesteFisico testeFisico) {

		repository.save(testeFisico);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(TesteFisico testeFisico) {

		repository.delete(testeFisico);

	}

	public TesteFisico findById(Long id) {

		return repository.findOne(id);

	}

	public List<TesteFisico> findAll() {
		return repository.findAll();
	}

	
	
	

	
	private Double formataMinutos(String valor) {
		if (valor.equals("0")) {
			valor = "00'00''00";
		}
		String soNumeros = valor.replace("'", "");

		String minutos = soNumeros.substring(0, 2);

		String segundos = soNumeros.substring(2, 4);

		String segundosToDecimal;
		if (!segundos.equals("00")) {
			segundosToDecimal = ((Integer.parseInt(segundos) * 100) / 60) + "";
		} else {
			segundosToDecimal = "00";
		}
		Double resultado;
		if (Integer.parseInt(segundosToDecimal) > 0 && Integer.parseInt(segundosToDecimal) < 10) {
			resultado = Double.parseDouble(minutos + ("0" + segundosToDecimal));
		} else {
			resultado = Double.parseDouble(minutos + segundosToDecimal);
		}

		// System.out.println("Resultado: "+resultado);

		return resultado;
	}

	

	public List<PessoaDef> pessoasIncluir(Set<PessoaDef> pessoasInclusas) {
		List<PessoaDef> pessoasIncluir = pessoaDefService.findAll();
		RemoveColecao.removeOfThis(pessoasIncluir, pessoasInclusas);
		

		return pessoasIncluir;
	}

}
