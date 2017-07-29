package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.repository.def.PessoaDefRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PessoaDefService {

	@Autowired
	private PessoaDefRepository repository;

	@Transactional(readOnly = false)
	public void save(PessoaDef pessoa) {

		repository.save(pessoa);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(PessoaDef pessoa) {

		repository.delete(pessoa);

	}

	public PessoaDef findById(Long id) {

		return repository.findOne(id);

	}

	public List<PessoaDef> findAll() {

		return repository.findAll();
	}

	public PessoaDef findOne(Long id) {
		return repository.findOne(id);
	}

	public PessoaDef buscaById(Long id) {

		return repository.buscaById(id);
	}

}
