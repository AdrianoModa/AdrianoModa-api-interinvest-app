package com.dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dev.domain.Papel;
import com.dev.domain.Status;
import com.dev.service.PapelService;

@RestController
@RequestMapping("/papel")
@CrossOrigin
public class PapelController {
	
	@Autowired
	private PapelService papelService;
	
	@GetMapping
	public List<Papel> listarTodos(){
		return papelService.buscarTodos();
	}
	
	@GetMapping("/{status}")
	public ResponseEntity<List<Papel>> listarPorStatus(@PathVariable Status status){
		if (!Status.ATIVA.name().equals("ATIVA") || !Status.ATIVA.name().equals("INATIVA")) {
			return new ResponseEntity<List<Papel>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Papel>>(papelService.buscarPorStatus(status), HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<Papel> adquirirPapel(@RequestBody @Validated Papel papel){
		return new ResponseEntity<Papel>(papelService.comprar(papel), HttpStatus.OK);
	}
	
	@PatchMapping("/{papelId}/preco")
	public ResponseEntity<?> atualizarPreco(@PathVariable Long papelId, @Validated @RequestBody Papel papel){
		sePapelExiste(papelId);
		return new ResponseEntity<Papel>(papelService.alterarPreco(papelId, papel), HttpStatus.OK);
	}	
	
	@PatchMapping("/{papelId}/status")
	public ResponseEntity<?> atualizarStatus(@PathVariable Long papelId, @Validated @RequestBody Papel papel){
		sePapelExiste(papelId);
		return new ResponseEntity<Papel>(papelService.alterarStatus(papelId, papel), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{papelId}")
	public ResponseEntity<Void> deletar(@PathVariable Long papelId){
		sePapelExiste(papelId);
		papelService.remover(papelId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);		
	}
	
	private void sePapelExiste(Long papelId) {
		Optional<Papel> papelExistente = papelService.buscarPorId(papelId);
		if (!papelExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O Papel não foi encontrado");
		}
	}

}
