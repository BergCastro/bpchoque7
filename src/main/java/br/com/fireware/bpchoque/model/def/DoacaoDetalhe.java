package br.com.fireware.bpchoque.model.def;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import br.com.fireware.bpchoque.model.def.Doacao.DoacaoTipo;
import lombok.Data;

@Data
@Entity
@Table(name = "doacoes_detalhes")
public class DoacaoDetalhe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Um tipo deve ser selecionado!")
	@Enumerated(EnumType.STRING)
	private DoacaoTipo tipo;

	@NotBlank(message = "Uma descrição deve ser inserida!")
	@Length(max = 40, message = "A descrição tem que ter no máximo {max} caracteres")
	private String descricao;

	@NotNull(message = "Um valor/quantidade deve ser inserida!")
	@Digits(integer = 6, fraction = 2, message = "Apenas números com até dois dígitos")
	@Min(value = 0, message = "O valor mínimo é 1")
	@Max(value = 100000, message = "O valor máximo é 100.000")
	private BigDecimal quantidade;

	@ManyToOne
	@JoinColumn(name = "doacao")
	private Doacao doacao;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		descricao = descricao.toUpperCase();

	}

}
