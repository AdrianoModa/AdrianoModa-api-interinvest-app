package com.dev.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.domain.Cliente;
import com.dev.domain.Investimento;
import com.dev.domain.Papel;
import com.dev.repository.ClienteRepository;
import com.dev.repository.InvestimentoRepository;
import com.dev.repository.PapelRepository;

@Service
public class InvestimentoService {
	
	@Autowired
	private InvestimentoRepository investimentoRepository;
	
	@Autowired
	private PapelRepository papelRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Investimento> buscarTodos(){
		String cpfCliente = "72368098291";
		Cliente cliente = new Cliente();
		cliente = clienteRepository.findClienteByCpf(cpfCliente);
		System.err.println(cliente.getCpf());
		return investimentoRepository.findAll();
	}
	
	public Stream<List<Double>> investir(Double valorAInvestir, String cpfCliente, int quantidadeEmpresaAInvestir) {
		
		Cliente cliente = new Cliente();
		cliente = clienteRepository.findClienteByCpf(cpfCliente);
		
//		List<Double> papeis = papelRepository.findAll().stream().filter(f -> f.getStatus().equals("ATIVA")).map(m -> m.getPreco()).collect(Collectors.toList());
		List<Double> papeis = Arrays.asList(28.45, 22.58, 42.11, 15.19, 38.15);
		
		double minimo = papeis.stream().mapToDouble(v -> v).min().getAsDouble();
		List<Double> papelList = new ArrayList<Double>();
		Set<Double> papelListLimit = new HashSet<Double>();

		Collections.shuffle(papeis);
		
		for (int i = 0; valorAInvestir >= minimo; i++) {
			if (i == papeis.size()) {
				i = 0;
			}
			
			if (papelListLimit.size() < quantidadeEmpresaAInvestir) {				
				if (papeis.get(i) <= valorAInvestir) {		
					papelList.add(papeis.get(i));
					papelListLimit.add(papeis.get(i));
					valorAInvestir = valorAInvestir - papeis.get(i);				
				}
			}else {
				papeis = new ArrayList<Double>();
				papeis.addAll(papelListLimit);
				papelListLimit.clear();
				minimo = papeis.stream().mapToDouble(v -> v).min().getAsDouble();
				i = 0;
			}
		}
		
		DecimalFormat df = new DecimalFormat("###.##");
		Stream<List<Double>> combinacao = Stream.of(papelList);
		Double totalAdquirido = papelList.stream().reduce((a, b) -> a + b).get();
		System.out.println(df.format(totalAdquirido));
		
		return combinacao;		
	}

}
