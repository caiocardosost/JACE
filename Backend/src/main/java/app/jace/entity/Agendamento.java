package app.jace.entity;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class Agendamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O nome do paciente é obrigatorio")
	private String paciente; //sera uma relacao com o obj paciente
	
	@NotBlank(message = "O nome do profissional é obrigatorio")
	private String profissional; //sera uma relacao com o obj prof
	
	@NotBlank(message = "O nome do procedimento é obrigatorio")
	private String procedimento; //sera uma relacao com o obj proced
	
	@NotBlank(message = "a sala é obrigatoria")
	private String sala; //sera uma relacao com o obj sala
	
	@NotNull(message = "A data é obrigatoria")
	private LocalDate data;
	
	@NotNull(message = "A data é obrigatoria")
	private LocalTime horarioInicio;
	
	@NotNull(message = "A data é obrigatoria")
	private LocalTime horarioFim;

	private String status = "AGENDADO";	
	
	private String observacoes;	
	

}
