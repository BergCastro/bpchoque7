//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "avaliadores")
public class Avaliador {

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

	private String cref;

	private Escolaridade escolaridade;
	
	
	
	
	@OneToOne()
	@JoinColumn(name = "pessoadef")
	private PessoaDef pessoadef;

	public enum Escolaridade {
		TECNICO("TÉCNICO"), GRADUADO("GRADUADO"), POSGRADUADO("PÓS-GRADUADO"), MESTRADO("MESTRADO"), DOUTORADO(
				"DOUTORADO");

		private String descricao;

		Escolaridade(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}
	}
	
	
	

}
