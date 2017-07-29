package br.com.fireware.bpchoque.repository.def;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.model.def.Comissao;
import br.com.fireware.bpchoque.model.def.TesteFisico;

public interface ComissaoRepository extends JpaRepository<Comissao, Long> {

	List<Comissao> findAll();
	List<Comissao> findByTesteFisico(TesteFisico testeFisico);

}
