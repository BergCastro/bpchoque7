//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "comissoes")
public class Comissao {

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

	@Enumerated(EnumType.STRING)	
	private Funcao funcao;
	
	
	@OneToOne()
	@JoinColumn(name = "avaliador")
	private Avaliador avaliador;
	
	
	@ManyToOne
	@JoinColumn(name="testeFisico")
	private TesteFisico testeFisico;

	
	
	
	public enum Funcao {
		AVALIADOR("Avaliador"), PH_TATICO("PH Tático"), SECRETARIO("Secretário"), PRESIDENTE("Presidente");

		private String descricao;

		Funcao(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}
	}

}
