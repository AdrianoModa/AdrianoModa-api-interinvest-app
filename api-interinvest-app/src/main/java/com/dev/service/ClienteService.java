package com.dev.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.domain.Cliente;
import com.dev.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> buscarTodos(){
		return clienteRepository.findAll();
	}

	public Cliente cadastrar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

}
