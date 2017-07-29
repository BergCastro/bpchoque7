//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "testes_fisicos")
public class TesteFisico {

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

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(columnDefinition = "DATE")
	private LocalDate data;

	private String objetivo;
	
	@ManyToMany()
	@JoinTable(name = "testes_tipos", joinColumns = @JoinColumn(name = "testeFisicoId"), inverseJoinColumns = @JoinColumn(name = "tipoTesteId"))
	private List<TipoTeste> tipos;

	/*
	 * @ManyToMany(fetch = FetchType.EAGER)
	 * 
	 * @JoinTable ( name ="testesFisicos_pessoas", joinColumns = @JoinColumn (
	 * name ="testeFisicoId"), inverseJoinColumns = @JoinColumn ( name
	 * ="pessoaId")) private List<PessoaDef> pessoas;
	 */

	private Double notaAprovacao;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		objetivo = objetivo.toUpperCase();

	}

}
