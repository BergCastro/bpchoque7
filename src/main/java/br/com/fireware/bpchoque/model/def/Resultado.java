package br.com.fireware.bpchoque.model.def;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;

import lombok.Data;
@Data
public abstract class Resultado {

	

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@ManyToOne
		@JoinColumn(name = "pessoa")
		private PessoaDef pessoa;

		@ManyToOne
		@OnDelete(action = OnDeleteAction.CASCADE)
		@JoinColumn(name = "teste")
		private TesteFisico teste;

				
		private Double notaFinal;

		

		

		

	

	
	
}
