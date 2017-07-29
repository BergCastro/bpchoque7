package br.com.fireware.bpchoque.service.def;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual;
import br.com.fireware.bpchoque.model.def.MedicaoAvaliacaoIndividual;
import br.com.fireware.bpchoque.repository.def.MedicaoAvaliacaoIndividualRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MedicaoAvaliacaoIndividualService {

	@Autowired
	private MedicaoAvaliacaoIndividualRepository repository;

	@Transactional(readOnly = false)
	public void save(MedicaoAvaliacaoIndividual medicaoAvalicaoIndividual) {

		repository.save(medicaoAvalicaoIndividual);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(MedicaoAvaliacaoIndividual medicaoAvaliacaoIndividual) {

		repository.delete(medicaoAvaliacaoIndividual);

	}

	public MedicaoAvaliacaoIndividual finById(Long id) {

		return repository.findOne(id);

	}

	public List<MedicaoAvaliacaoIndividual> findAll() {
		return repository.findAll();
	}

	public Iterable<MedicaoAvaliacaoIndividual> findByAvaliacaoindividual(AvaliacaoIndividual ava) {

		return repository.findByAvaliacaoindividual(ava);
	}

}
