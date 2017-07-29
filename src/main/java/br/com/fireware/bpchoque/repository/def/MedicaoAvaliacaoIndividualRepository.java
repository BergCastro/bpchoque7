package br.com.fireware.bpchoque.repository.def;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fireware.bpchoque.model.def.AvaliacaoIndividual;
import br.com.fireware.bpchoque.model.def.MedicaoAvaliacaoIndividual;

public interface MedicaoAvaliacaoIndividualRepository extends JpaRepository<MedicaoAvaliacaoIndividual, Long> {

	List<MedicaoAvaliacaoIndividual> findAll();

	List<MedicaoAvaliacaoIndividual> findByAvaliacaoindividual(AvaliacaoIndividual ava);

}
