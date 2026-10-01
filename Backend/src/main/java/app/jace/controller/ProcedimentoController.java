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

import app.jace.entity.Procedimento;
import app.jace.service.ProcedimentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/procedimento")
@CrossOrigin("*")
public class ProcedimentoController {
	
	//INJEÇÂO DE DEPENDENCIA DO SERVICE
	private ProcedimentoService proceServ;
	
	public ProcedimentoController (ProcedimentoService proceServ) {
		this.proceServ = proceServ;
	}
	
	
	//------------ENDPOINTS----------------
	
	// Novo registro
	@PostMapping("/novo")
	public ResponseEntity<String> registarProcedimento(@Valid @RequestBody Procedimento procedimento){
		try {
			String resposta = this.proceServ.registrarProcedimento(procedimento);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao salvar", HttpStatus.BAD_REQUEST);

		}
	}
	
	
	// Editar Procedimento
	@PutMapping("/editar/{id}")
	public ResponseEntity<String> editarProcedimento(@PathVariable long id, @Valid @RequestBody Procedimento procedimento){
		try {
			String resposta = this.proceServ.editarProcedimento(id, procedimento);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao editar", HttpStatus.BAD_REQUEST);

		}
	}
	
	// Deletar Procedimento
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<String> deletarProcedimento(@PathVariable long id){
		try {
			String resposta = this.proceServ.deletarProcedimento(id);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao remover", HttpStatus.BAD_REQUEST);
		}
	}
	
	// Buscar Procedimento
	@GetMapping("/busca/{id}")
	public ResponseEntity<Procedimento> buscaProcedimento(@PathVariable long id){
		try {
			Procedimento resposta = this.proceServ.buscaProcedimentoId(id);
			return new ResponseEntity<Procedimento>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			Procedimento resposta = null;
			return new ResponseEntity<Procedimento>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	
	// Busca todos os Procedimento
	@GetMapping("/buscatodos")
	public ResponseEntity<List<Procedimento>> buscaProcedimento(){
		try {
			List<Procedimento> resposta = this.proceServ.buscaProcedimentoTodos();
			return new ResponseEntity<List<Procedimento>>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			List<Procedimento> resposta = null;
			return new ResponseEntity<List<Procedimento>>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
		

}
