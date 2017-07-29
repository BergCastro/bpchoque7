//CIA(COD_CIA, NOME_CIA)
package br.com.fireware.bpchoque.model;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
@Entity
@Table(name="opms_orgaos")
public class OpmOrgao{
	
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private Long id;
	@NotEmpty(message="O campo não pode estar vazio!")
	@Length(max=40, message="O campo tem que ter no máximo {max} caracteres")
	@Column(name="nome", nullable=false, length=40, unique=true)
	private String nome;
	
	@Column(name="localizacao", nullable=true, length=60)
	private String localizacao;
	@NotNull(message="Escolha um tipo!")
	@Enumerated(EnumType.STRING)
	private TipoOpm tipo;
	
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
	
	public enum TipoOpm {
		CIVIL("Civil"),
		MILITAR("Militar");
		
		private String descricao;
		
		TipoOpm(String descricao) {
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
	}
	
	
	
	
}
