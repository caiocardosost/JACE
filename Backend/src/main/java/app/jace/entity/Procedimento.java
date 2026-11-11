package app.jace.entity;

import java.math.BigDecimal;

import app.jace.enums.StatusProcedimento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Procedimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O nome é obrigatorio")
	@Column(unique = true)
	private String nome;
	
	@Column(length = 1000)
	private String descricao;
	
	private Integer duracaoEmMinutos;
	
	@NotNull(message = "O preço é obrigatório")
	@PositiveOrZero(message = "O preço não pode ser negativo")
	@Column(precision = 10, scale = 2)
	private BigDecimal precoBase;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusProcedimento status = StatusProcedimento.ATIVO;	


}
