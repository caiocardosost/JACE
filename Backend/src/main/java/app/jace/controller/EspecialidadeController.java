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

import app.jace.entity.Especialidade;
import app.jace.service.EspecialidadeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/especialidade")
@CrossOrigin("*")
public class EspecialidadeController {
	
	// INJEÇÂO DE DEPENDENCIA DO SERVICE
	private EspecialidadeService espServ;
	
	public EspecialidadeController (EspecialidadeService espServ) {
		this.espServ = espServ;
	}
	
	
	//------------ENDPOINTS----------------
	
	// Novo registro
	@PostMapping("/novo")
	public ResponseEntity<String> registarEspecialidade(@Valid @RequestBody Especialidade especialidade){
		try {
			String resposta = this.espServ.registrarEspecialidade(especialidade);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao salvar", HttpStatus.BAD_REQUEST);

		}
	}
	
	
	// Editar Especialidade
	@PutMapping("/editar/{id}")
	public ResponseEntity<String> editarEspecialidade(@PathVariable long id, @Valid @RequestBody Especialidade especialidade){
		try {
			String resposta = this.espServ.editarEspecialidade(id, especialidade);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao editar", HttpStatus.BAD_REQUEST);

		}
	}
	
	// Deletar Especialidade
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<String> deletarEspecialidade(@PathVariable long id){
		try {
			String resposta = this.espServ.deletarEspecialidade(id);
			return new ResponseEntity<String>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao remover", HttpStatus.BAD_REQUEST);
		}
	}
	
	// Buscar Especialidade
	@GetMapping("/busca/{id}")
	public ResponseEntity<Especialidade> buscaEspecialidade(@PathVariable long id){
		try {
			Especialidade resposta = this.espServ.buscaEspecialidadeId(id);
			return new ResponseEntity<Especialidade>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			Especialidade resposta = null;
			return new ResponseEntity<Especialidade>(resposta, HttpStatus.BAD_REQUEST);
		}
	}
	
	// Busca todas as Especialidade
	@GetMapping("/buscatodos")
	public ResponseEntity<List<Especialidade>> buscaEspecialidade(){
		try {
			List<Especialidade> resposta = this.espServ.buscaEspecialidadeTodas();
			return new ResponseEntity<List<Especialidade>>(resposta, HttpStatus.OK);
			
		} catch (Exception e) {
			List<Especialidade> resposta = null;
			return new ResponseEntity<List<Especialidade>>(resposta, HttpStatus.BAD_REQUEST);
		}
	}

}
