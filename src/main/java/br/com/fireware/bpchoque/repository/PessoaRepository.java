package br.com.fireware.bpchoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import br.com.fireware.bpchoque.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	List<Pessoa> findAll();
	List<Pessoa> findByNome(String nome);
	@Query("select p from Pessoa p where p.id=?1")
	Pessoa buscaById(Long id);
	
}
