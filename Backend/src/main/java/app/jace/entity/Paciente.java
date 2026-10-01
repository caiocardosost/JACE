package app.jace.entity;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = " O nome é obrigatorio")
	private String nome;
	
	@NotBlank(message = "Obrigatorio informar o cpf")
	@Column(unique = true)
	private String cpf;
	
	@NotNull(message = "Obrigatorio inserir a data de nascimento")
	private LocalDate data_nascimento;
	
	@NotNull (message = "Obrigatorio inserir o numero de telefone")
	private String telefone;
	
	private String email;
	
	@NotNull (message = "Obrigatorio inserir o endereco")
	private String endereco;
	
	private String status = "ATIVO";
	
		
}
