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

import app.jace.entity.Receita;
import app.jace.service.ReceitaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/receita")
@CrossOrigin("*")
public class ReceitaController {
	
	//INJEÇÂO DE DEPENDENCIA DO SERVICE
	private ReceitaService recServ;
	
	public ReceitaController (ReceitaService recServ) {
		this.recServ = recServ;
	}
	
	
	//------------ENDPOINTS----------------
	
	// Novo registro
	@PostMapping("/novo")
	public ResponseEntity<String> registarReceita(@Valid @RequestBody Receita receita){
		try {
			String resposta = this.recServ.registrarReceita(receita);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao salvar", HttpStatus.BAD_REQUEST);

		}
	}
	
	
	// Editar Receita
	@PutMapping("/editar/{id}")
	public ResponseEntity<String> editarReceita(@PathVariable long id, @Valid @RequestBody Receita receita){
		try {
			String resposta = this.recServ.editarReceita(id, receita);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao editar", HttpStatus.BAD_REQUEST);

		}
	}
	
	// Deletar Receita
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<String> deletarReceita(@PathVariable long id){
		try {
			String resposta = this.recServ.deletarReceita(id);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao remover", HttpStatus.BAD_REQUEST);
		}
	}
	
	// Buscar Receita
	@GetMapping("/busca/{id}")
	public ResponseEntity<Receita> buscaReceita(@PathVariable long id){
		try {
			Receita resposta = this.recServ.buscaReceitaId(id);
			return new ResponseEntity<Receita>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			Receita resposta = null;
			return new ResponseEntity<Receita>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	
	// Busca todas as Receitas
	@GetMapping("/buscatodos")
	public ResponseEntity<List<Receita>> buscaReceitas(){
		try {
			List<Receita> resposta = this.recServ.buscaReceitaTodas();
			return new ResponseEntity<List<Receita>>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			List<Receita> resposta = null;
			return new ResponseEntity<List<Receita>>(resposta, HttpStatus.BAD_REQUEST);
		}
	}

}
