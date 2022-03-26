package com.dev.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.domain.Investimento;
import com.dev.service.InvestimentoService;

@RestController
@RequestMapping("/investimento")
@CrossOrigin
public class InvestimentoController {
	
	@Autowired
	private InvestimentoService investimentoService;
	
	@GetMapping
	public ResponseEntity<List<Investimento>> listarTodos(){
		return new ResponseEntity<List<Investimento>>(investimentoService.buscarTodos(), HttpStatus.OK);
	}
	
	@GetMapping("/{valorAInvestir}/{cpfCliente}/{quantidadeEmpresaAInvestir}")
	public ResponseEntity<Stream<List<Double>>> investimento(
			@PathVariable Double valorAInvestir,
			@PathVariable String cpfCliente,
			@PathVariable int quantidadeEmpresaAInvestir){
		return 
				new ResponseEntity<Stream<List<Double>>>(investimentoService
						.investir(valorAInvestir, cpfCliente, quantidadeEmpresaAInvestir), HttpStatus.OK);
	}

}
