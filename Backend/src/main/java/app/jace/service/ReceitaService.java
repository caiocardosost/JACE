package app.jace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.jace.entity.Receita;
import app.jace.repository.ReceitaRepository;

@Service
public class ReceitaService {
	
	//INJEÇÃO DE DEPENDENCIA DO REPOSITORY
	
	private ReceitaRepository recRepo;
	
	public ReceitaService(ReceitaRepository recRepo) {
		this.recRepo = recRepo;
	}
	
	
	// CRUD
	
	// Registrar nova Receita
	public String registrarReceita(Receita receita) {
		this.recRepo.save(receita);
		return "Receita registrada com sucesso!";
	}
	
	// Editar Receita
	public String editarReceita(long id, Receita receita) {
		receita.setId(id);
		this.recRepo.save(receita);
		return "Receita editada com sucesso!";
	}
	
	// Deletar Receita
	public String deletarReceita(long id) {
		this.recRepo.deleteById(id);
		return "Receita deletada com sucesso!";
	}
	
	// Buscar Receita
	public Receita buscaReceitaId(long id) {
		Receita receita = this.recRepo.findById(id).get();
		return receita;
	}
	
	// buscar todas as Receita
	public List<Receita> buscaReceitaTodas() {
		List<Receita> receita = this.recRepo.findAll();
		return receita;
	}

	
}
