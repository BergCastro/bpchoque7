package br.com.fireware.bpchoque.repository.def;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.ResultadoTafEspecial;
import br.com.fireware.bpchoque.model.def.ResultadoTafGeral;

import br.com.fireware.bpchoque.model.def.TesteFisico;

public interface ResultadoTafEspecialRepository extends JpaRepository<ResultadoTafEspecial, Long> {

	List<ResultadoTafEspecial> findByTeste(TesteFisico teste);

	List<ResultadoTafEspecial> findByPessoa(PessoaDef pessoa);

}
