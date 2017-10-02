package br.com.fireware.bpchoque.model.def;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "resultadosTheCdc")
public class ResultadoTheCdc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pessoa")
	private PessoaDef pessoa;

	@ManyToOne
	@JoinColumn(name = "teste")
	private TesteFisico teste;

	private Integer inscricao;
		
	@Enumerated(EnumType.STRING)
	private EnumAptoInapto flutuacao;
	
	@Enumerated(EnumType.STRING)
	private EnumAptoInapto corrida5km;
	
	@Enumerated(EnumType.STRING)
	private EnumAptoInapto lancarGranada;
	
	@Enumerated(EnumType.STRING)
	private EnumAptoInapto natacao50m;
	
	

	@PrePersist
	@PreUpdate
	private void prePersistPreUpdate() {

	}

	@PostLoad
	private void postLoad() {

	}

}
