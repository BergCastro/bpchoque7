package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.TipoTeste;
import br.com.fireware.bpchoque.repository.def.TipoTesteRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TipoTesteService {

	@Autowired
	private TipoTesteRepository repository;

	@Transactional(readOnly = false)
	public void save(TipoTeste tipoTeste) {

		repository.save(tipoTeste);

	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(TipoTeste tipoTeste) {

		repository.delete(tipoTeste);

	}

	public TipoTeste findById(Long id) {

		return repository.findOne(id);

	}
	
	

	public List<TipoTeste> findAll() {
		return repository.findAll();
	}
	
	

}
