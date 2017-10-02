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
@Table(name = "resultadosTheCoesp")
public class ResultadoTheCoesp {

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
	
	private String corrida10km;
	
	private Integer corrida10kmPts;
	
	@Enumerated(EnumType.STRING)
	private AptoInaptoTentativa subidaCorda;
	
	
	private String transporteCarga;
	
	private Integer transporteCargaPts;
	
	@Enumerated(EnumType.STRING)
	private EnumAptoInapto saltoPlataforma;
	
	@Enumerated(EnumType.STRING)
	private EnumAptoInapto apneiaEstatica;
	
	private String natacao200m;
	
	private Integer natacao200mPts;
	
	@Enumerated(EnumType.STRING)
	private EnumAptoInapto deslocMeioLiquido;
	
	
	@Enumerated(EnumType.STRING)
	private EnumAptoInapto flutuacao;
	
	@Enumerated(EnumType.STRING)
	private EnumAptoInapto mergulhoLivre;
	

	@PrePersist
	@PreUpdate
	private void prePersistPreUpdate() {

	}

	@PostLoad
	private void postLoad() {

	}
	
	public enum AptoInaptoTentativa {
		APTO_PRIMEIRA_TENTATIVA("Apto-1ªTentativa"), APTO_SEG_TENTATIVA("Apto-2ª°Tentativa"),
		APTO_TER_TENTATIVA("Apto-3ªTentativa");

		private String descricao;

		AptoInaptoTentativa(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}
	}

}
