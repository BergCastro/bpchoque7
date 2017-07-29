package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.ResultadoTeste;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.model.def.TipoTeste;
import br.com.fireware.bpchoque.repository.def.ResultadoTesteRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ResultadoTesteService {

	@Autowired
	private ResultadoTesteRepository repository;

	@Transactional(readOnly = false)
	public void save(ResultadoTeste resultadoTeste) {

		repository.save(resultadoTeste);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(ResultadoTeste resultadoTeste) {

		repository.delete(resultadoTeste);

	}

	public ResultadoTeste findById(Long id) {

		return repository.findOne(id);

	}

	public List<ResultadoTeste> findByTeste(TesteFisico testeFisico) {

		return repository.findByTeste(testeFisico);

	}
	
	public List<ResultadoTeste> findByTipoTeste(TipoTeste tipo) {

		return repository.findByTipoTeste(tipo);

	}

	public List<ResultadoTeste> findByPessoa(PessoaDef pessoa) {

		return repository.findByPessoa(pessoa);

	}

	public List<ResultadoTeste> findAll() {
		return repository.findAll();
	}

}
