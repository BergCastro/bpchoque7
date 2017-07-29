package br.com.fireware.bpchoque.repository.def;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual;


public interface AvaliacaoIndividualRepository extends JpaRepository<AvaliacaoIndividual, Long> {
	
	List<AvaliacaoIndividual> findAll();
	

}
