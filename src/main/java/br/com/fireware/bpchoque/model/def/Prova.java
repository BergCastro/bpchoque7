//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "provas")
public class Prova {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "criadoem")
	private LocalDateTime criadoem;

	@Column(name = "criadopor")
	private String criadopor;

	@DateTimeFormat(pattern = "dd/MM/yyyy  HH:mm:ss")
	@Column(name = "atualizadoem")
	private LocalDateTime atualizadoem;

	@Column(name = "atualizadopor")
	private String atualizadopor;

	@NotBlank(message = "Um nome de prova deve ser inserido!")
	private String nome;
	@NotBlank(message = "Uma descrição de prova deve ser inserida!")
	private String descricao;

	@NotNull(message = "Um tipo deve ser selecionado!")
	@Enumerated(EnumType.STRING)
	private CampoTipo tipo;

	// private BigDecimal valorProva;

	private String refInicialMasc;

	private String refFinalMasc;

	private String refInicialFem;

	private String refFinalFem;

	private String intervaloRef;
	
	private String valorTentativa1;
	
	private String valorTentativa2;
	
	private String valorTentativa3;

	private Integer idadeInicial;

	private Integer idadeFinal;

	private Integer intervaloIdade;
	
	 //@ManyToMany(mappedBy="provas")
	 //private List<TipoTeste> tiposTeste;

	/*
	 * @OneToMany(mappedBy="prova") private List<ResultadoTeste> resultados;
	 */

	// @OneToMany(orphanRemoval=true, mappedBy="doacao")
	// private List<DoacaoDetalhe> detalhes;

	// private double valor;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		nome = nome.toUpperCase();

	}

	public enum CampoTipo {
		INTEIRO("Inteiro"), APTOINAPTO("Apto / Inapto"), TEMPO("Tempo (s)"),
		TEMPO_MIN("Tempo (m)"), TENTATIVA("Tentativa");

		private String descricao;

		CampoTipo(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}

	}

	public enum AptoInapto {
		APTO("Apto"), INAPTO("Inapto");

		
		private String descricao;

		AptoInapto(String descricao) {
			this.descricao = descricao;
			
		}

		public String getDescricao() {
			return descricao;
		}

		

	}

}
