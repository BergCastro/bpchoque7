package br.com.fireware.bpchoque.repository.def;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;




import br.com.fireware.bpchoque.model.def.TesteFisico;


public interface TesteFisicoRepository extends JpaRepository<TesteFisico, Long> {
	
	List<TesteFisico> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
	
	

}
