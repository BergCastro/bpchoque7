package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.Prova;
import br.com.fireware.bpchoque.repository.def.ProvaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ProvaService {

	@Autowired
	private ProvaRepository repository;

	@Transactional(readOnly = false)
	public void save(Prova prova) {

		repository.save(prova);

	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(Prova prova) {

		repository.delete(prova);

	}

	public Prova findById(Long id) {

		return repository.findOne(id);

	}
	
	

	public List<Prova> findAll() {
		return repository.findAll();
	}
	
	

}
