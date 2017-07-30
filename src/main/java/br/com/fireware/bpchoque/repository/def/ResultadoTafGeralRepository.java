package br.com.fireware.bpchoque.repository.def;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.ResultadoTafGeral;

import br.com.fireware.bpchoque.model.def.TesteFisico;

public interface ResultadoTafGeralRepository extends JpaRepository<ResultadoTafGeral, Long> {

	List<ResultadoTafGeral> findByTeste(TesteFisico teste);

	List<ResultadoTafGeral> findByPessoa(PessoaDef pessoa);

}
