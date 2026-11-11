package app.jace.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import app.jace.enums.StatusAgendamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertTrue;
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
		
	@NotNull(message = "A data é obrigatoria")
	private LocalDate data;
	
	@NotNull(message = "A hora de inicio é obrigatoria")
	private LocalTime horarioInicio;
	
	@NotNull(message = "A data é obrigatoria")
	private LocalTime horarioFim;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusAgendamento status = StatusAgendamento.AGENDADO;	
	
	@Column(length = 1000)
	private String observacoes;	
	
	@Column(length = 500)
	private String motivoCancelamento;
	
	
	  
	@AssertTrue(message = "O horário de término deve ser depois do início")
	public boolean isHorarioValido() {
		return horarioInicio == null || horarioFim == null || horarioFim.isAfter(horarioInicio);
	}	

}
