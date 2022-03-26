package com.dev.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.domain.Cliente;
import com.dev.service.ClienteService;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listarTodos(){
		return clienteService.buscarTodos();
	}
	
	@PostMapping
	public ResponseEntity<Cliente> registrarCliente(@RequestBody @Validated Cliente cliente){
		return new ResponseEntity<Cliente>(clienteService.cadastrar(cliente), HttpStatus.CREATED);
	}

}
