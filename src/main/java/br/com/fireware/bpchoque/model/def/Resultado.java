//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;

import java.util.List;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Data;

@Data

public class Resultado {

	private Long pessoa;

	private List<Long> provas;

	private List<String> valores;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {

	}

}
