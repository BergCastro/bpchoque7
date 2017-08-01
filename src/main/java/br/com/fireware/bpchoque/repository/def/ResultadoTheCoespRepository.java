package br.com.fireware.bpchoque.repository.def;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.model.def.PessoaDef;

import br.com.fireware.bpchoque.model.def.ResultadoTheCoesp;
import br.com.fireware.bpchoque.model.def.TesteFisico;

public interface ResultadoTheCoespRepository extends JpaRepository<ResultadoTheCoesp, Long> {

	List<ResultadoTheCoesp> findByTeste(TesteFisico teste);

	List<ResultadoTheCoesp> findByPessoa(PessoaDef pessoa);

}
