package app.jace.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.jace.entity.Profissional;
import app.jace.service.ProfissionalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profissional")
public class ProfissionalController {
	
	//INJEÇÂO DE DEPENDENCIA DO SERVICE
	private ProfissionalService proServ;
	
	public ProfissionalController (ProfissionalService proServ) {
		this.proServ = proServ;
	}
	
	
	//------------ENDPOINTS----------------
	
	//Novo registro
	@PostMapping("/novo")
	public ResponseEntity<String> registarProfissional(@Valid @RequestBody Profissional profissional){
		try {
			String resposta = this.proServ.registrarProfissional(profissional);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("erro ao salvar", HttpStatus.BAD_REQUEST);

		}
	}
	
	
	//Editar Profissional
	@PutMapping("/editar/{id}")
	public ResponseEntity<String> editarProfissional(@PathVariable long id, @Valid @RequestBody Profissional profissional){
		try {
			String resposta = this.proServ.editarProfissional(id, profissional);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("erro ao editar", HttpStatus.BAD_REQUEST);

		}
	}
	
	//deletar Profissional
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<String> deletarProfissional(@PathVariable long id){
		try {
			String resposta = this.proServ.deletarProfissional(id);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("erro ao remover", HttpStatus.BAD_REQUEST);
		}
	}
	
	// Buscar Profissional
	@GetMapping("/busca/{id}")
	public ResponseEntity<Profissional> buscaProfissional(@PathVariable long id){
		try {
			Profissional resposta = this.proServ.buscaProfissionalId(id);
			return new ResponseEntity<Profissional>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			Profissional resposta = null;
			return new ResponseEntity<Profissional>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	
	// Busca todos os Profissional
	@GetMapping("/buscatodos")
	public ResponseEntity<List<Profissional>> buscaPaciente(){
		try {
			List<Profissional> resposta = this.proServ.buscaProfissionalTodos();
			return new ResponseEntity<List<Profissional>>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			List<Profissional> resposta = null;
			return new ResponseEntity<List<Profissional>>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	
}
