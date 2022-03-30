package com.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dev.domain.Investimento;
import com.dev.service.InvestimentoService;

@RestController
@RequestMapping("/investimento")
@CrossOrigin
public class InvestimentoController {
	
	@Autowired
	private InvestimentoService investimentoService;
	
	@GetMapping
	@Cacheable(value = "investimento")
	public ResponseEntity<List<Investimento>> listarTodos(){
		return new ResponseEntity<List<Investimento>>(investimentoService.buscarTodos(), HttpStatus.OK);
	}
	
	@PostMapping("/{valorAInvestir}/{cpfCliente}/{quantidadeEmpresaAInvestir}")
	public ResponseEntity<Investimento> adquirirPapel(
			@PathVariable Double valorAInvestir,
			@PathVariable String cpfCliente,
			@PathVariable int quantidadeEmpresaAInvestir){
		
		if (cpfCliente.length() != 11) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return 
				new ResponseEntity<Investimento>(investimentoService
						.investir(valorAInvestir, cpfCliente, quantidadeEmpresaAInvestir), HttpStatus.OK);
	}

}
