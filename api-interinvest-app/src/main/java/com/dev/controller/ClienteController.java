package com.dev.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
