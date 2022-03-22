package com.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.domain.Investimento;
import com.dev.repository.InvestimentoRepository;

@Service
public class InvestimentoService {
	
	@Autowired
	private InvestimentoRepository investimentoRepository;
	
	public List<Investimento> buscarTodos(){
		return investimentoRepository.findAll();
	}

}
