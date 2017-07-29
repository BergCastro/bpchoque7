package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fireware.bpchoque.model.def.Comissao;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.repository.def.ComissaoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ComissaoService {

	@Autowired
	private ComissaoRepository repository;

	@Transactional(readOnly = false)
	public void save(Comissao comissao) {

		repository.save(comissao);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(Comissao comissao) {

		repository.delete(comissao);

	}

	public Comissao finById(Long id) {

		return repository.findOne(id);

	}
	public List<Comissao> findByTesteFisico(TesteFisico testeFisico) {

		return repository.findByTesteFisico(testeFisico);

	}

	public List<Comissao> findAll() {
		return repository.findAll();
	}

}
