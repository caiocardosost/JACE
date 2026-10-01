package app.jace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.jace.entity.Paciente;
import app.jace.repository.PacienteRepository;

@Service
public class PacienteService {
	
	//INJEÇÃO DE DEPENDENCIA DO REPOSITORY
	
	private PacienteRepository pacRepo;
	
	public PacienteService(PacienteRepository pacRepo) {
		this.pacRepo = pacRepo;
	}
	
	
	// CRUD
	
	// Registrar novo paciente
	public String registrarPaciente(Paciente paciente) {
		this.pacRepo.save(paciente);
		return "Paciente registrado com sucesso!";
	}
	
	// Editar Paciente
	public String editarPaciente(long id, Paciente paciente) {
		paciente.setId(id);
		this.pacRepo.save(paciente);
		return "Paciente editado com sucesso!";
	}
	
	// Deletar Paciente
	public String deletarPaciente(long id) {
		this.pacRepo.deleteById(id);
		return "Paciente deletado com sucesso!";
	}
	
	//buscar paciente
	public Paciente buscaPacienteId(long id) {
		Paciente paciente = this.pacRepo.findById(id).get();
		return paciente;
	}
	
	//buscar todos os pacientes
	public List<Paciente> buscaPacienteTodos() {
		List<Paciente> pacientes = this.pacRepo.findAll();
		return pacientes;
	}


}
