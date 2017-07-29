//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "tipos_teste")
public class TipoTeste {

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
	//@NotBlank(message="Um nome deve ser inserido!")
	private String nome;
	//@NotBlank(message="Uma descrição deve ser inserida!")
	private String descricao;
	@NotNull(message="O número de provas para a média não pode está vazio!")
	@Min(value=1, message="O valor não pode ser abaixo 1")
	private Integer qtdProvasMedia;
	
	private Boolean variaIdade;
	
	private Boolean variaSexo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tiposteste_provas", joinColumns = @JoinColumn(name = "tipoTesteId"), inverseJoinColumns = @JoinColumn(name = "provaId"))
	private Set<Prova> provas;

	/*
	 * @OneToMany(mappedBy="tipoTeste", fetch = FetchType.EAGER) private
	 * List<ResultadoTeste> resultados;
	 */

	@Transient
	private Integer qtdProvas;

	public Integer getQtdProvas() {
		return provas.size();
	}

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		nome = nome.toUpperCase();
		descricao = descricao.toUpperCase();

	}

}
