package app.jace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.jace.entity.Anamnese;
import app.jace.repository.AnamneseRepository;

@Service
public class AnamneseService {
	
	//INJEÇÃO DE DEPENDENCIA DO REPOSITORY
	
	private AnamneseRepository anRepo;
	
	public AnamneseService(AnamneseRepository anRepo) {
		this.anRepo = anRepo;
	}
	
	
	// CRUD
	
	// Registrar nova Anamnese
	public String registrarAnamnese(Anamnese anamnese) {
		this.anRepo.save(anamnese);
		return "Anamnese registrada com sucesso!";
	}
	
	// Editar Anamnese
	public String editarAnamnese(long id, Anamnese anamnese) {
		anamnese.setId(id);
		this.anRepo.save(anamnese);
		return "Anamnese editada com sucesso!";
	}
	
	// Deletar Anamnese
	public String deletarAnamnese(long id) {
		this.anRepo.deleteById(id);
		return "Anamnese deletada com sucesso!";
	}
	
	// Buscar Anamnese
	public Anamnese buscaAnamneseId(long id) {
		Anamnese anamnese = this.anRepo.findById(id).get();
		return anamnese;
	}
	
	// buscar todos os Anamnese
	public List<Anamnese> buscaAnamneseTodas() {
		List<Anamnese> anamneses = this.anRepo.findAll();
		return anamneses;
	}
	
}
