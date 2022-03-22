package com.dev.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
