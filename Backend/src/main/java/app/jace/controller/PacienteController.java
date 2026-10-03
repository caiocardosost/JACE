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

import app.jace.entity.Paciente;
import app.jace.service.PacienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/paciente")
@CrossOrigin("*")
public class PacienteController {
	
	//INJEÇÂO DE DEPENDENCIA DO SERVICE
	private PacienteService pacServ;
	
	public PacienteController (PacienteService pacServ) {
		this.pacServ = pacServ;
	}
	
	
	//------------ENDPOINTS----------------
	
	// Novo registro
	@PostMapping("/novo")
	public ResponseEntity<String> registarPaciente(@Valid @RequestBody Paciente paciente){
		try {
			String resposta = this.pacServ.registrarPaciente(paciente);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao salvar", HttpStatus.BAD_REQUEST);

		}
	}
	
	
	// Editar paciente
	@PutMapping("/editar/{id}")
	public ResponseEntity<String> editarPaciente(@PathVariable long id, @Valid @RequestBody Paciente paciente){
		try {
			String resposta = this.pacServ.editarPaciente(id, paciente);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao editar", HttpStatus.BAD_REQUEST);

		}
	}
	
	// Deletar paciente
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<String> deletarPaciente(@PathVariable long id){
		try {
			String resposta = this.pacServ.deletarPaciente(id);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao remover", HttpStatus.BAD_REQUEST);
		}
	}
	
	// Buscar paciente
	@GetMapping("/busca/{id}")
	public ResponseEntity<Paciente> buscaPaciente(@PathVariable long id){
		try {
			Paciente resposta = this.pacServ.buscaPacienteId(id);
			return new ResponseEntity<Paciente>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			Paciente resposta = null;
			return new ResponseEntity<Paciente>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	
	// Busca todos os pacientes
	@GetMapping("/buscatodos")
	public ResponseEntity<List<Paciente>> buscaPacientes(){
		try {
			List<Paciente> resposta = this.pacServ.buscaPacienteTodos();
			return new ResponseEntity<List<Paciente>>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			List<Paciente> resposta = null;
			return new ResponseEntity<List<Paciente>>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
		

}
