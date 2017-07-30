package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.ResultadoTafGeral;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.repository.def.ResultadoTafGeralRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ResultadoTafGeralService {

	@Autowired
	private ResultadoTafGeralRepository repository;

	@Transactional(readOnly = false)
	public void save(ResultadoTafGeral resultadoTeste) {

		repository.save(resultadoTeste);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(ResultadoTafGeral resultadoTeste) {

		repository.delete(resultadoTeste);

	}

	public ResultadoTafGeral findById(Long id) {

		return repository.findOne(id);

	}

	public List<ResultadoTafGeral> findByTeste(TesteFisico testeFisico) {

		return repository.findByTeste(testeFisico);

	}
	
	

	public List<ResultadoTafGeral> findByPessoa(PessoaDef pessoa) {

		return repository.findByPessoa(pessoa);

	}

	public List<ResultadoTafGeral> findAll() {
		return repository.findAll();
	}

}
