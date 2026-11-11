package app.jace.entity;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import org.hibernate.validator.constraints.br.CPF;

import app.jace.enums.Sexo;
import app.jace.enums.StatusPaciente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Sexo sexo;
	
	@NotBlank(message = " O CPF é obrigatorio")
	@CPF(message = "CPF inválido")
	@Column(unique = true, length = 11)
	private String cpf;
	
	@NotNull(message = "Obrigatorio inserir a data de nascimento")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "O telefone é obrigatório")
	private String telefone;
	
	private String email;
	
	@NotBlank(message = "O endereço é obrigatório")
	private String endereco;
	
	private String responsavelNome;
	
	@CPF(message = "CPF inválido")
	@Column(length = 11)
	private String responsavelCpf;

	private String responsavelTelefone;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusPaciente status = StatusPaciente.ATIVO;
	
	
	
	
	// Calculado a cada leitura. Não existe coluna "idade" no banco, mas propriedade 'idade' será gerado em tempo de execução.
	public Integer getIdade() {
		if (dataNascimento == null) return null;
		return Period.between(dataNascimento, LocalDate.now(ZoneId.of("America/Sao_Paulo"))).getYears();
	}
		
}
