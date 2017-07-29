package br.com.fireware.bpchoque.repository.def;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.fireware.bpchoque.model.def.Doacao;


public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
	
	List<Doacao> findAll();
	

}
