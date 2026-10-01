package app.jace.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import app.jace.entity.Agendamento;
import app.jace.repository.AgendamentoRepository;

@Service
public class AgendamentoService {
	//INJEÇÃO DE DEPENDENCIA DO REPOSITORY
	
	private AgendamentoRepository agRepo;
	
	public AgendamentoService(AgendamentoRepository agRepo) {
		this.agRepo = agRepo;
	}
	
	
	// CRUD
	
	// Registrar novo Agendamento
	public String registrarAgendamento(Agendamento agendamento) {
		if (this.temColisao(agendamento)) {
			return "Ja existe um agendamento para este dia e horario";
		}
		this.agRepo.save(agendamento);
		return "Agendamento registrado com sucesso!";
	}
	
	// Editar Agendamento
	public String editarAgendamento(long id, Agendamento agendamento) {
		agendamento.setId(id);
		this.agRepo.save(agendamento);
		return "Agendamento editado com sucesso!";
	}
	
	// Deletar Agendamento
	public String deletarAgendamento(long id) {
		this.agRepo.deleteById(id);
		return "Agendamento deletada com sucesso!";
	}
	
	// Buscar Agendamento
	public Agendamento buscaAgendamentoId(long id) {
		Agendamento agendamento = this.agRepo.findById(id).get();
		return agendamento;
	}
	
	// buscar todos os Agendamentos
	public List<Agendamento> buscaAgendamentoTodos() {
		List<Agendamento> agendamentos = this.agRepo.findAll();
		return agendamentos;
	}
	
	// REGRAS DE NEGOCIO
	
	// Verificando colisoes
	
	public boolean temColisao(Agendamento agendamento) {
		List<Agendamento> agendamentos = this.agRepo.findByData(agendamento.getData());
		if (agendamentos.isEmpty()) {
			return false;
		}
		for (Agendamento age : agendamentos) {
			if (this.temColisaoHorario(age.getHorarioInicio(), age.getHorarioFim(), agendamento.getHorarioInicio(), agendamento.getHorarioFim())) {
				return true;
			}
		}
		return false;
    }
	
	// Verificando colisoes de horario
	public boolean temColisaoHorario(LocalTime inicio1, LocalTime fim1, LocalTime inicio2, LocalTime fim2) {
        // Regra:
        // (Inicio1 < Fim2) E (Fim1 > Inicio2)
        return inicio1.isBefore(fim2) && fim1.isAfter(inicio2);
    }
	
	
	
}
