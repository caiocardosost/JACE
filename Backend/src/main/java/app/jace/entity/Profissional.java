package app.jace.entity;


import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import org.hibernate.validator.constraints.br.CPF;

import app.jace.enums.Sexo;
import app.jace.enums.StatusProfissional;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Profissional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O nome é obrigatorio")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Sexo sexo;
	
	@NotBlank(message = "Obrigatorio informar o cpf")
	@CPF(message = "CPF inválido")
	@Column(unique = true, length = 11)
	private String cpf;
	
	@NotNull(message = "Obrigatorio inserir a data de nascimento")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "É obrigatório informar o CRO")
	@Pattern(
	    regexp = "^[A-Z]{2}-\\d+$",
	    message = "Informe o CRO no formato UF-número. Ex.: SP-123456"
	)
	@Column(unique = true)
	private String cro;
	
	@NotBlank(message = "O endereço é obrigatório")
	private String endereco;
	
	@NotBlank(message = "O telefone é obrigatório")
	private String telefone;
	
	private String email;
		
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusProfissional status = StatusProfissional.ATIVO;
	
	
	// Calculado a cada leitura. Não existe coluna "idade" no banco, mas propriedade 'idade' será gerado em tempo de execução.
	public Integer getIdade() {
		if (dataNascimento == null) return null;
		return Period.between(dataNascimento, LocalDate.now(ZoneId.of("America/Sao_Paulo"))).getYears();
	}
	
}
