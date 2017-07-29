package br.com.fireware.bpchoque.service.def;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.Doacao;
import br.com.fireware.bpchoque.repository.def.DoacaoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DoacaoService {

	@Autowired
	private DoacaoRepository repository;

	@Transactional(readOnly = false)
	public void save(Doacao doacao) {

		repository.save(doacao);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(Doacao doacao) {

		repository.delete(doacao);

	}

	public Doacao findById(Long id) {

		return repository.findOne(id);

	}

	public List<Doacao> findAll() {
		return repository.findAll();
	}

}
