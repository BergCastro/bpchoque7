package br.com.fireware.bpchoque.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import br.com.fireware.bpchoque.model.OpmOrgao;
import br.com.fireware.bpchoque.model.OpmOrgao.TipoOpm;



public interface OpmOrgaoRepository extends JpaRepository<OpmOrgao, Long>{

	Iterable<OpmOrgao> findByTipo(TipoOpm tipo);

	

}
