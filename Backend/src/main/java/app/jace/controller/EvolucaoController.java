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

import app.jace.entity.Evolucao;
import app.jace.service.EvolucaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/evolucao")
@CrossOrigin("*")
public class EvolucaoController {
	
	//INJEÇÂO DE DEPENDENCIA DO SERVICE
	private EvolucaoService evServ;
	
	public EvolucaoController (EvolucaoService evServ) {
		this.evServ = evServ;
	}
	
	
	//------------ENDPOINTS----------------
	
	// Novo registro
	@PostMapping("/novo")
	public ResponseEntity<String> registarEvolucao(@Valid @RequestBody Evolucao evolucao){
		try {
			String resposta = this.evServ.registrarEvolucao(evolucao);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao salvar", HttpStatus.BAD_REQUEST);

		}
	}
	
	
	// Editar Evolucao
	@PutMapping("/editar/{id}")
	public ResponseEntity<String> editarEvolucao(@PathVariable long id, @Valid @RequestBody Evolucao evolucao){
		try {
			String resposta = this.evServ.editarEvolucao(id, evolucao);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao editar", HttpStatus.BAD_REQUEST);

		}
	}
	
	// Deletar Evolucao
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<String> deletarEvolucao(@PathVariable long id){
		try {
			String resposta = this.evServ.deletarEvolucao(id);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao remover", HttpStatus.BAD_REQUEST);
		}
	}
	
	// Buscar Evolucao
	@GetMapping("/busca/{id}")
	public ResponseEntity<Evolucao> buscaEvolucao(@PathVariable long id){
		try {
			Evolucao resposta = this.evServ.buscaEvolucaoId(id);
			return new ResponseEntity<Evolucao>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			Evolucao resposta = null;
			return new ResponseEntity<Evolucao>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	
	// Busca todos as Evolucoes
	@GetMapping("/buscatodos")
	public ResponseEntity<List<Evolucao>> buscaEvolucoes(){
		try {
			List<Evolucao> resposta = this.evServ.buscaEvolucaoTodas();
			return new ResponseEntity<List<Evolucao>>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			List<Evolucao> resposta = null;
			return new ResponseEntity<List<Evolucao>>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	

}
