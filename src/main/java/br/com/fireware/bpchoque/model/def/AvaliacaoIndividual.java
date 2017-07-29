//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import lombok.Data;

@Data
@Entity
@Table(name = "avaliacoes_individuais")
public class AvaliacaoIndividual {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	// @NotNull(message="O campo não pode estar vazio!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")

	private LocalDate dataAvaliacao;

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

	@NotNull(message = "Um avaliado deve ser selecionado")
	@ManyToOne()
	@JoinColumn(name = "pessoadef")
	private PessoaDef pessoadef;

	@NotNull(message = "Um avaliador deve ser selecionado")
	@ManyToOne()
	@JoinColumn(name = "avaliador")
	private Avaliador avaliador;

	@NotNull(message = "Uma pergunta não foi respondida!")
	private Boolean praticaTipoAtividade;

	private String praticaTipoAtividadeQual;

	@NotNull(message = "Uma pergunta não foi respondida!")
	@Enumerated(EnumType.STRING)
	private Frequencia frequencia;

	@NotNull(message = "Uma pergunta não foi respondida!")
	@Enumerated(EnumType.STRING)
	private Duracao duracao;

	@NotNull(message = "Uma pergunta não foi respondida!")
	@Enumerated(EnumType.STRING)
	private Objetivos objetivo;

	private String objetivoOutro;

	@NotNull(message = "Uma pergunta não foi respondida!")
	private Boolean restricao;

	private String restricaoQual;
	@NotNull(message = "Uma pergunta não foi respondida!")
	private Boolean fuma;
	@NotNull(message = "Uma pergunta não foi respondida!")
	private Boolean bebidaAlcoolica;
	@NotNull(message = "Uma pergunta não foi respondida!")
	private Boolean medicamento;

	private String medicamentoQual;

	private String medicamentoMotivo;

	private String medicamentoDosagem;

	@ElementCollection(targetClass = Problemas.class)
	@Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but
									// defaults to ORDINAL.
	@CollectionTable(name = "avaliacao_problemas", joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
	@Column(name = "problemas") // Column name in person_interest
	private List<Problemas> problemas;

	@OneToMany(mappedBy = "avaliacaoindividual", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MedicaoAvaliacaoIndividual> medicoes = new ArrayList<>();

	private String problemaOutro;

	private String problemaArticulacaoQual;

	public enum Frequencia {
		UMA_VEZ_POR_SEMANA("Uma vez por semana"), DUAS_VEZES_POR_SEMANA("Duas vezes por semana"), TRES_VEZES_POR_SEMANA(
				"Três vezes por semana"), QUATRO_VEZES_POR_SEMANA(
						"Quatro vezes por semana"), SOMENTE_AOS_FINAIS_DE_SEMANA(
								"Somente aos finais de semana"), TODOS_OS_DIAS("Todos os dias");

		private String descricao;

		Frequencia(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}
	}

	public enum Duracao {
		MENOS_DE_30MIN("Menos de 30 minutos"), TRINTA_MIN("Trinta minutos"), ENTRE_40_A_50MIN(
				"Entre 40 a 50 minutos"), UMA_HORA("Uma hora"), ACIMA_DE_1H("Acima de uma hora");

		private String descricao;

		Duracao(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}
	}

	public enum Objetivos {
		EMAGRECIMENTO("Emagrecimento"), QUALIDADE_DE_VIDA("Qualidade de vida"), GANHO_DE_MASSA_MUSCULAR(
				"Ganho de massa muscular"), CONDICIONAMENTO("Condicionamento"), REABILITACAO(
						"Reabilitação"), TREINAMENTO_ESPORTIVO(
								"Treinamento Esportivos"), LAZER("Lazer"), OUTROS("Outros");

		private String descricao;

		Objetivos(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}
	}

	public enum Problemas {
		ARTICULACAO("Articulação"), HIPERTENSAO("Hipertensão"), DIABETES("Diabetes"), RENAL("Renal"), OBESIDADE(
				"Obesidade"), HERNIA_DE_DISCO("Hernia de disco"), CARDIOPATIA("Cardiopata"), OUTROS("Outros");

		private String descricao;

		Problemas(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}
	}

}
