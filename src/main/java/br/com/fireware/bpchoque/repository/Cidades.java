package br.com.fireware.bpchoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.model.Cidade;
import br.com.fireware.bpchoque.model.Estado;
import br.com.fireware.bpchoque.repository.helper.cidade.CidadesQueries;

public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQueries {

	public List<Cidade> findByEstadoId(Long idEstado);

	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
	
}
