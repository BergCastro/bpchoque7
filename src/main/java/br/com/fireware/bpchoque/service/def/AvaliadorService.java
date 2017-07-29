package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fireware.bpchoque.model.def.Avaliador;
import br.com.fireware.bpchoque.repository.def.AvaliadorRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class AvaliadorService {

	@Autowired
	private AvaliadorRepository repository;

	@Transactional(readOnly = false)
	public void save(Avaliador avaliador) {

		repository.save(avaliador);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(Avaliador avaliador) {

		repository.delete(avaliador);

	}

	public Avaliador finById(Long id) {

		return repository.findOne(id);

	}

	public List<Avaliador> findAll() {
		return repository.findAll();
	}

}
