package app.jace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.jace.entity.Evolucao;
import app.jace.repository.EvolucaoRepository;

@Service
public class EvolucaoService {
	
	//INJEÇÃO DE DEPENDENCIA DO REPOSITORY
	
	private EvolucaoRepository evRepo;
	
	public EvolucaoService(EvolucaoRepository evRepo) {
		this.evRepo = evRepo;
	}
	
	
	// CRUD
	
	// Registrar nova Evolucao
	public String registrarEvolucao(Evolucao evolucao) {
		this.evRepo.save(evolucao);
		return "Evolucao registrada com sucesso!";
	}
	
	// Editar Evolucao
	public String editarEvolucao(long id, Evolucao evolucao) {
		evolucao.setId(id);
		this.evRepo.save(evolucao);
		return "Evolucao editada com sucesso!";
	}
	
	// Deletar Evolucao
	public String deletarEvolucao(long id) {
		this.evRepo.deleteById(id);
		return "Evolucao deletada com sucesso!";
	}
	
	// Buscar Evolucao
	public Evolucao buscaEvolucaoId(long id) {
		Evolucao evolucao = this.evRepo.findById(id).get();
		return evolucao;
	}
	
	// buscar todas as Evolucoes
	public List<Evolucao> buscaEvolucaoTodas() {
		List<Evolucao> evolucoes = this.evRepo.findAll();
		return evolucoes;
	}

	
}
