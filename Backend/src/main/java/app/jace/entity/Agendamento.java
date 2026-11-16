package app.jace.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import app.jace.enums.StatusAgendamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertTrue;
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
	
	@ManyToOne
    @JoinColumn(name = "paciente_id")
	private Paciente paciente; // Relacao 1-N com o obj paciente
	
	@ManyToOne
    @JoinColumn(name = "profissional_id")
	private Profissional profissional; // Relacao 1-N com o obj prof
	
	@ManyToMany
    @JoinTable(name = "agendamento_procedimento")
	private List<Procedimento> procedimento; // Relacao N-N o obj proced
		
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
