package br.com.fireware.bpchoque.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{

	public Cargo findByNome(String nome);
	
}
