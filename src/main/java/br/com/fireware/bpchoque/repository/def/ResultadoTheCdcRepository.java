package br.com.fireware.bpchoque.repository.def;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.ResultadoTafGeral;
import br.com.fireware.bpchoque.model.def.ResultadoTeste;
import br.com.fireware.bpchoque.model.def.ResultadoTheCdc;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.model.def.TipoTeste;



public interface ResultadoTheCdcRepository extends JpaRepository<ResultadoTheCdc, Long> {
	
	List<ResultadoTheCdc> findByTeste(TesteFisico teste);
	List<ResultadoTheCdc> findByPessoa(PessoaDef pessoa);

}
