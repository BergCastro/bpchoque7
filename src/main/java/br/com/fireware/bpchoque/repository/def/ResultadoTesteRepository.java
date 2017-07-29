package br.com.fireware.bpchoque.repository.def;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.ResultadoTeste;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.model.def.TipoTeste;



public interface ResultadoTesteRepository extends JpaRepository<ResultadoTeste, Long> {
	
	List<ResultadoTeste> findByTeste(TesteFisico teste);
	List<ResultadoTeste> findByTipoTeste(TipoTeste tipo);
	List<ResultadoTeste> findByTesteAndTipoTeste(TesteFisico teste, TipoTeste tipoTeste);
	List<ResultadoTeste> findByPessoa(PessoaDef pessoa);

}
