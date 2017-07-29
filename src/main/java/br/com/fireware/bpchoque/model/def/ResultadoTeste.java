//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "resultados_teste")
public class ResultadoTeste {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pessoa")
	private PessoaDef pessoa;

	@ManyToOne
	@JoinColumn(name = "teste")
	private TesteFisico teste;

	@ManyToOne
	@JoinColumn(name = "tipoTeste")
	private TipoTeste tipoTeste;

	private Long prova1;

	private String tipoPontuacaoProva1;

	private String valorProva1;

	private Double pontuacaoProva1;

	private Long prova2;

	private String tipoPontuacaoProva2;

	private String valorProva2;

	private Double pontuacaoProva2;

	private Long prova3;

	private String tipoPontuacaoProva3;

	private String valorProva3;

	private Double pontuacaoProva3;

	private Long prova4;

	private String tipoPontuacaoProva4;

	private String valorProva4;

	private Double pontuacaoProva4;

	private Long prova5;

	private String tipoPontuacaoProva5;

	private String valorProva5;

	private Double pontuacaoProva5;
	
	private Long prova6;

	private String tipoPontuacaoProva6;

	private String valorProva6;

	private Double pontuacaoProva6;
	
	private Integer idade;


	private Double notaFinal;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {

	}

}
