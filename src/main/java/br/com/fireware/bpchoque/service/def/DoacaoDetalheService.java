package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.Doacao;
import br.com.fireware.bpchoque.model.def.DoacaoDetalhe;
import br.com.fireware.bpchoque.repository.def.DoacaoDetalheRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DoacaoDetalheService {

	@Autowired
	private DoacaoDetalheRepository repository;

	@Transactional(readOnly = false)
	public DoacaoDetalhe save(DoacaoDetalhe doacaoDetalhe) {

		return repository.saveAndFlush(doacaoDetalhe);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(DoacaoDetalhe doacaoDetalhe) {

		repository.delete(doacaoDetalhe);

	}

	public DoacaoDetalhe finById(Long id) {

		return repository.findOne(id);

	}

	public List<DoacaoDetalhe> findAll() {
		return repository.findAll();
	}

	public List<DoacaoDetalhe> findByDoacao(Doacao doacao) {
		return repository.findByDoacao(doacao);
	}

	@Transactional(readOnly = false)
	public void deleteByDoacao(Doacao doacao) {

		repository.deleteByDoacao(doacao);

	}

}
