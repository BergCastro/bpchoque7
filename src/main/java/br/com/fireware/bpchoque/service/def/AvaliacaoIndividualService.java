package br.com.fireware.bpchoque.service.def;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual;

import br.com.fireware.bpchoque.repository.def.AvaliacaoIndividualRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class AvaliacaoIndividualService {

	@Autowired
	private AvaliacaoIndividualRepository repository;

	@Transactional(readOnly = false)
	public void save(AvaliacaoIndividual avalicaoIndividual) {

		repository.save(avalicaoIndividual);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(AvaliacaoIndividual avaliacaoIndividual) {

		repository.delete(avaliacaoIndividual);

	}

	public AvaliacaoIndividual finById(Long id) {

		return repository.findOne(id);

	}

	public List<AvaliacaoIndividual> findAll() {
		return repository.findAll();
	}
	
	

}
