package br.com.fireware.bpchoque.model.def;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "resultadosTafGeral")
public class ResultadoTafGeral {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pessoa")
	private PessoaDef pessoa;

	@ManyToOne
	@JoinColumn(name = "teste")
	private TesteFisico teste;

	private Integer idade;

	private Integer flexaoBarra;

	private Integer flexaoBarraPts;

	private Integer flexaoSolo;

	private Integer flexaoSoloPts;

	private Integer abdominal;

	private Integer abdominalPts;

	private String corrida50m;

	private Integer corrida50mPts;

	private Integer corrida12min;

	private Integer corrida12minPts;

	private Double notaFinal;

	@PrePersist
	@PreUpdate
	private void prePersistPreUpdate() {

	}

	@PostLoad
	private void postLoad() {

	}

}
