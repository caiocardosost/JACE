package app.jace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.jace.entity.Procedimento;
import app.jace.repository.ProcedimentoRepository;

@Service
public class ProcedimentoService {
	
	//INJEÇÃO DE DEPENDENCIA DO REPOSITORY
	
	private ProcedimentoRepository proceRepo;
	
	public ProcedimentoService(ProcedimentoRepository proceRepo) {
		this.proceRepo = proceRepo;
	}
	
	
	// CRUD
	
	// Registrar novo Procedimento
	public String registrarProcedimento(Procedimento procedimento) {
		this.proceRepo.save(procedimento);
		return "Procedimento registrado com sucesso!";
	}
	
	// Editar Procedimento
	public String editarProcedimento(long id, Procedimento procedimento) {
		procedimento.setId(id);
		this.proceRepo.save(procedimento);
		return "Procedimento editado com sucesso!";
	}
	
	// Deletar Procedimento
	public String deletarProcedimento(long id) {
		this.proceRepo.deleteById(id);
		return "Procedimento deletada com sucesso!";
	}
	
	// Buscar Procedimento
	public Procedimento buscaProcedimentoId(long id) {
		Procedimento procedimento = this.proceRepo.findById(id).get();
		return procedimento;
	}
	
	// buscar todos os Procedimentos
	public List<Procedimento> buscaProcedimentoTodos() {
		List<Procedimento> procedimentos = this.proceRepo.findAll();
		return procedimentos;
	}
	
	

}
