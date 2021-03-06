package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.ResultadoTheCoesp;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.repository.def.ResultadoTheCoespRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ResultadoTheCoespService {

	@Autowired
	private ResultadoTheCoespRepository repository;

	@Transactional(readOnly = false)
	public void save(ResultadoTheCoesp resultadoTeste) {

		repository.save(resultadoTeste);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(ResultadoTheCoesp resultadoTeste) {

		repository.delete(resultadoTeste);

	}

	public ResultadoTheCoesp findById(Long id) {

		return repository.findOne(id);

	}

	public List<ResultadoTheCoesp> findByTeste(TesteFisico testeFisico) {

		return repository.findByTeste(testeFisico);

	}
	
	

	public List<ResultadoTheCoesp> findByPessoa(PessoaDef pessoa) {

		return repository.findByPessoa(pessoa);

	}

	public List<ResultadoTheCoesp> findAll() {
		return repository.findAll();
	}

}
