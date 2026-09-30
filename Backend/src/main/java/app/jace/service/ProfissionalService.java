package app.jace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.jace.entity.Profissional;
import app.jace.repository.ProfissionalRepository;

@Service
public class ProfissionalService {
	//INJEÇÃO DE DEPENDENCIA DO REPOSITORY
	
	private ProfissionalRepository proRepo;
	
	public ProfissionalService(ProfissionalRepository proRepo) {
		this.proRepo = proRepo;
	}
	
	
	// CRUD
	
	// Registrar novo Profissional
	public String registrarProfissional(Profissional profissional) {
		this.proRepo.save(profissional);
		return "Profissional registrado com sucesso!";
	}
	
	// Editar Profissional
	public String editarProfissional(long id, Profissional profissional) {
		profissional.setId(id);
		this.proRepo.save(profissional);
		return "Profissional editado com sucesso!";
	}
	
	// Editar Profissional
	public String deletarProfissional(long id) {
		this.proRepo.deleteById(id);
		return "Profissional deletado com sucesso!";
	}
	
	// buscar Profissional
	public Profissional buscaProfissionalId(long id) {
		Profissional profissional = this.proRepo.findById(id).get();
		return profissional;
	}
	
	// buscar todos os Profissionais
	public List<Profissional> buscaProfissionalTodos() {
		List<Profissional> profissionais = this.proRepo.findAll();
		return profissionais;
	}
	
	

}
