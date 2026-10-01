package app.jace.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
	
	private String descricao;
	
	private Integer duracaoEmMinutos;
	
	private BigDecimal precoBase;
	
	private String status = "ATIVO";	


}
