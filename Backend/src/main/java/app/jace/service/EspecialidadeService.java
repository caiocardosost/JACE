package app.jace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.jace.entity.Especialidade;
import app.jace.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {
	//INJEÇÃO DE DEPENDENCIA DO REPOSITORY
	
	private EspecialidadeRepository espRepo;
	
	public EspecialidadeService(EspecialidadeRepository espRepo) {
		this.espRepo = espRepo;
	}
	
	
	// CRUD
	
	// Registrar nova Especialidade
	public String registrarEspecialidade(Especialidade especialidade) {
		this.espRepo.save(especialidade);
		return "Especialidade registrado com sucesso!";
	}
	
	// Editar Especialidade
	public String editarEspecialidade(long id, Especialidade especialidade) {
		especialidade.setId(id);
		this.espRepo.save(especialidade);
		return "Especialidade editado com sucesso!";
	}
	
	// Deletar Especialidade
	public String deletarEspecialidade(long id) {
		this.espRepo.deleteById(id);
		return "Especialidade deletada com sucesso!";
	}
	
	// buscar Especialidade
	public Especialidade buscaEspecialidadeId(long id) {
		Especialidade especialidade = this.espRepo.findById(id).get();
		return especialidade;
	}
	
	// buscar todos as Especialidades
	public List<Especialidade> buscaEspecialidadeTodas() {
		List<Especialidade> especialidades = this.espRepo.findAll();
		return especialidades;
	}
	
	

}
