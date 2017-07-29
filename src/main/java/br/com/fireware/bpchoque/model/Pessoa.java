/**
 PESSOA(COD_PESSOA, NOME_PESSOA, LOGRADOURO_PESSOA, NUMERO_END_PESSOA,
  COMPLEMENTO_END_PESSOA, COD_CIDADE, 
 COD_BAIRRO, COD_UF, CEP_PESSOA, DATA_NASC_PESSOA,
  EMAIL_PESSOA, CPF_PESSOA, COD_ESTADO_CIVIL, COD_SEXO)
 **/

package br.com.fireware.bpchoque.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.Period;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.fireware.bpchoque.model.Cargo;

import br.com.fireware.bpchoque.model.OpmOrgao;
import br.com.fireware.bpchoque.model.Sexo;

import lombok.Data;

@Data
@Table(name = "pessoas")
@Entity
											
public class Pessoa{

	

	public enum EstadoCivil {
		CASADO("Casado"),
		SOLTEIRO("Solteiro");
		
		private String descricao;
		
		EstadoCivil(String descricao) {
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
	}
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private Long id;
	@NotEmpty(message = "O nome não pode estar vazio!")
	@Length(max = 60, message = "O nome tem que ter no máximo {max} caracteres")
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;

	@NotNull(message = "Um sexo deve ser selecionado!")
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
	private Sexo sexo;

	//@NotEmpty(message = "O campo não pode está vazio")
	//@Length(max = 30, message = "O logradouro tem que ter no máximo {max} caracteres")
	
	
	@Length(max = 16,  message = "Número de telefone inválido!")
	@Column(name = "telefone1", length = 16)
	private String telefone1;
	
	

	@Length(max = 16,  message = "Número de telefone inválido!")
	@Column(name = "telefone2", length = 16)
	private String telefone2;
	
	
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
	
	@NotNull(message="A Data de Nasc. não pode estar vazia")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate datanasc;
	
	

	
	@Email(message = "Insira um email válido!")
	@Length(max = 40, message = "O e-mail tem que ter no máximo {max} caracteres")
	@Column(name = "email", length = 40)
	private String email;

	
	

	
	@NotEmpty(message = "A matrícula não pode estar vazia!")
	//@Digits(integer = 9, fraction = 0, message = "Digite apenas números neste campo!")
	//@Length(max = 10, min = 10, message = "A matrícula tem que ter {max} caracteres")
	@Column(name = "matricula", nullable = false, length = 15, unique = true)
	private String matricula;

	@NotNull(message = "Um cargo deve ser selecionado!")
	@ManyToOne(optional = true)
	@JoinColumn(name = "cargo")
	private Cargo cargo;

	
	//@Digits(integer = 6, fraction = 0, message = "Digite apenas números neste campo!")
	//@Length(max = 6, message = "O número tem que ter no máximo {max} caracteres")
	@Column(name = "numero_pm", length = 6)
	private String numero_pm;

	@NotEmpty(message = "O nome de guerra não pode estar vazio!")
	//@Length(max = 20, message = "O campo tem que ter no máximo {max} caracteres")
	@Column(name = "nome_guerra", length = 20)
	private String nome_guerra;

	
	
	@NotNull(message = "Uma OPM deve ser selecionada!")
	@ManyToOne()
	@JoinColumn(name = "opm")
	private OpmOrgao opm;
	
	
	
	
	
	
	
	public static int idade(final LocalDate aniversario) {
	    final LocalDate dataAtual = LocalDate.now();
	    final Period periodo = Period.between(aniversario, dataAtual);
	    return periodo.getYears();
	}
	
	public static int idadeAvaliacao(final LocalDate aniversario) {
	    final int anoAtual = LocalDate.now().getYear();
	    final int anoAniversario = aniversario.getYear();
	    final int resultado = anoAtual-anoAniversario; 
	    
	    return resultado;
	}
	
	
	

}
