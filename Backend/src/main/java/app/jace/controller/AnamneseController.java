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

import app.jace.entity.Anamnese;
import app.jace.service.AnamneseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/anamnese")
@CrossOrigin("*")
public class AnamneseController {
	
	//INJEÇÂO DE DEPENDENCIA DO SERVICE
	private AnamneseService anServ;
	
	public AnamneseController (AnamneseService anServ) {
		this.anServ = anServ;
	}
	
	
	//------------ENDPOINTS----------------
	
	// Novo registro
	@PostMapping("/novo")
	public ResponseEntity<String> registarAnamnese(@Valid @RequestBody Anamnese anamnese){
		try {
			String resposta = this.anServ.registrarAnamnese(anamnese);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao salvar", HttpStatus.BAD_REQUEST);

		}
	}
	
	
	// Editar Anamnese
	@PutMapping("/editar/{id}")
	public ResponseEntity<String> editarAnamnese(@PathVariable long id, @Valid @RequestBody Anamnese anamnese){
		try {
			String resposta = this.anServ.editarAnamnese(id, anamnese);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao editar", HttpStatus.BAD_REQUEST);

		}
	}
	
	// Deletar Agendamento
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<String> deletarAnamnese(@PathVariable long id){
		try {
			String resposta = this.anServ.deletarAnamnese(id);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao remover", HttpStatus.BAD_REQUEST);
		}
	}
	
	// Buscar Anamnese
	@GetMapping("/busca/{id}")
	public ResponseEntity<Anamnese> buscaAnamnese(@PathVariable long id){
		try {
			Anamnese resposta = this.anServ.buscaAnamneseId(id);
			return new ResponseEntity<Anamnese>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			Anamnese resposta = null;
			return new ResponseEntity<Anamnese>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	
	// Busca todos as Anamnese
	@GetMapping("/buscatodos")
	public ResponseEntity<List<Anamnese>> buscaAnamneses(){
		try {
			List<Anamnese> resposta = this.anServ.buscaAnamneseTodas();
			return new ResponseEntity<List<Anamnese>>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			List<Anamnese> resposta = null;
			return new ResponseEntity<List<Anamnese>>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	

}
