package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.ResultadoTheCdc;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.repository.def.ResultadoTheCdcRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ResultadoTheCdcService {

	@Autowired
	private ResultadoTheCdcRepository repository;

	@Transactional(readOnly = false)
	public void save(ResultadoTheCdc resultadoTeste) {

		repository.save(resultadoTeste);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(ResultadoTheCdc resultadoTeste) {

		repository.delete(resultadoTeste);

	}

	public ResultadoTheCdc findById(Long id) {

		return repository.findOne(id);

	}

	public List<ResultadoTheCdc> findByTeste(TesteFisico testeFisico) {

		return repository.findByTeste(testeFisico);

	}
	
	

	public List<ResultadoTheCdc> findByPessoa(PessoaDef pessoa) {

		return repository.findByPessoa(pessoa);

	}

	public List<ResultadoTheCdc> findAll() {
		return repository.findAll();
	}

}
