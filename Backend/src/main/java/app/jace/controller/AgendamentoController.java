package app.jace.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.jace.entity.Agendamento;
import app.jace.service.AgendamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agendamento")
@CrossOrigin("*")
public class AgendamentoController {

	//INJEÇÂO DE DEPENDENCIA DO SERVICE
	private AgendamentoService agServ;
	
	public AgendamentoController (AgendamentoService agServ) {
		this.agServ = agServ;
	}
	
	
	//------------ENDPOINTS----------------
	
	// Novo registro
	@PostMapping("/novo")
	public ResponseEntity<String> registarAgendamento(@Valid @RequestBody Agendamento agendamento){
		try {
			String resposta = this.agServ.registrarAgendamento(agendamento);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao salvar", HttpStatus.BAD_REQUEST);

		}
	}
	
	
	// Editar Agendamento
	@PutMapping("/editar/{id}")
	public ResponseEntity<String> editarAgendamento(@PathVariable long id, @Valid @RequestBody Agendamento agendamento){
		try {
			String resposta = this.agServ.editarAgendamento(id, agendamento);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao editar", HttpStatus.BAD_REQUEST);

		}
	}
	
	// Deletar Agendamento
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<String> deletarAgendamento(@PathVariable long id){
		try {
			String resposta = this.agServ.deletarAgendamento(id);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao remover", HttpStatus.BAD_REQUEST);
		}
	}
	
	// Buscar Agendamento
	@GetMapping("/busca/{id}")
	public ResponseEntity<Agendamento> buscaAgendamento(@PathVariable long id){
		try {
			Agendamento resposta = this.agServ.buscaAgendamentoId(id);
			return new ResponseEntity<Agendamento>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			Agendamento resposta = null;
			return new ResponseEntity<Agendamento>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	
	// Busca todos os Agendamento
	@GetMapping("/buscatodos")
	public ResponseEntity<List<Agendamento>> buscaAgendamento(){
		try {
			List<Agendamento> resposta = this.agServ.buscaAgendamentoTodos();
			return new ResponseEntity<List<Agendamento>>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			List<Agendamento> resposta = null;
			return new ResponseEntity<List<Agendamento>>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	


}
