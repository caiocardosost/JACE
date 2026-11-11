package app.jace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.jace.entity.Paciente;
import app.jace.exception.RegraNegocioException;
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

	    validarResponsavel(paciente);

	    paciente.setCpf(normalizarCpf(paciente.getCpf()));

	    if (paciente.getResponsavelCpf() != null
	            && !paciente.getResponsavelCpf().isBlank()) {
	        paciente.setResponsavelCpf(
	            normalizarCpf(paciente.getResponsavelCpf())
	        );
	    }

	    this.pacRepo.save(paciente);

	    return "Paciente registrado com sucesso!";
	}
	
	// Editar paciente
	public String editarPaciente(long id, Paciente paciente) {

	    validarResponsavel(paciente);

	    paciente.setId(id);
	    paciente.setCpf(normalizarCpf(paciente.getCpf()));

	    if (paciente.getResponsavelCpf() != null
	            && !paciente.getResponsavelCpf().isBlank()) {
	        paciente.setResponsavelCpf(
	            normalizarCpf(paciente.getResponsavelCpf())
	        );
	    }

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
	
	// Regras de negócio
	
	// recuperando cpf em numeros
	private String normalizarCpf(String cpf) {
	    return cpf.replaceAll("\\D", "");
	}
	
	// Responsavel legal (pacientes < 18 anos)
	private void validarResponsavel(Paciente paciente) {
	    Integer idade = paciente.getIdade();

	    if (idade != null && idade < 18) {

	        if (paciente.getResponsavelNome() == null
	                || paciente.getResponsavelNome().isBlank()) {
	            throw new RegraNegocioException(
	                "Informe o nome do responsável legal."
	            );
	        }

	        if (paciente.getResponsavelTelefone() == null
	                || paciente.getResponsavelTelefone().isBlank()) {
	            throw new RegraNegocioException(
	                "Informe o telefone do responsável legal."
	            );
	        }

	        if (paciente.getResponsavelCpf() == null
	                || paciente.getResponsavelCpf().isBlank()) {
	            throw new RegraNegocioException(
	                "Informe o CPF do responsável legal."
	            );
	        }
	    }
	}


}
