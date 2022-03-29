package com.dev.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.domain.Cliente;
import com.dev.domain.Investimento;
import com.dev.domain.Papel;
import com.dev.domain.PapelTotal;
import com.dev.domain.Status;
import com.dev.repository.ClienteRepository;
import com.dev.repository.InvestimentoRepository;

@Service
public class InvestimentoService {
	
	@Autowired
	private InvestimentoRepository investimentoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;	
	
	public List<Investimento> buscarTodos(){
		return investimentoRepository.findAll();
	}
	
	public Investimento investir(Double valorAInvestir, String cpfCliente, int quantidadeEmpresaAInvestir) {
		
		Cliente cliente = clienteRepository.findDistinctClienteByCpf(cpfCliente);
		
		List<Papel> papeis = cliente.getPapeis()
				.stream().filter(f -> f.getStatus().equals(Status.ATIVA))
				.collect(Collectors.toList());
		
		Comparator<Papel> comparator = Comparator.comparing(Papel::getPreco);
		Double valorInvestido = valorAInvestir;
		
		Papel minimo = papeis.stream().min(comparator).get();
		List<Papel> papelList = new ArrayList<Papel>();
		List<Papel> papelListLimit = new ArrayList<Papel>();
		
		Collections.shuffle(papeis);
		
		for (int i = 0; valorAInvestir >= minimo.getPreco(); i++) {
			if (i == papeis.size()) {
				i = 0;
			}
			
			if (papelListLimit.size() < quantidadeEmpresaAInvestir) {			
				if (papeis.get(i).getPreco() <= valorAInvestir) {		
					papelList.add(papeis.get(i));
					papelListLimit.add(papeis.get(i));
					valorAInvestir = valorAInvestir - papeis.get(i).getPreco();		
				}
			}else {
				papeis = new ArrayList<Papel>();
				papeis.addAll(papelListLimit);
				papelListLimit.clear();
				minimo = papeis.stream().min(Comparator.comparing(Papel::getPreco)).get();
				i = 0;
			}
		}
		
		Optional<Double> valorTotalCompra = papelList.parallelStream()
				.map(m -> m.getPreco()).reduce((a, b) -> Double.sum(a, b));
		
		Map<String, Double> totalPorPapel = totalPorPapelAdquirido(papelList);
		
		List<PapelTotal> totalPorPapelLista = new ArrayList<PapelTotal>();		
		
		totalPorPapel.forEach((key, value) -> {
			List<String> nome = papelList.stream()
					.filter(f -> f.getTicker().equals(key))
					.map(Papel::getNome)
					.collect(Collectors.toList());
			totalPorPapelLista.add(new PapelTotal(nome.get(0), key, value));
		});
		
		Double troco = (valorInvestido - valorTotalCompra.get());
		
		Investimento investir = new Investimento();
		
		investir.setQuantidadeDePapeis(papelList.size());
		investir.setValorTotalCompra(valorTotalCompra.get());
		investir.setValorAInvestir(valorInvestido);
		investir.setTroco(troco);
		investir.setPapeisComprados(papelList);
		investir.setValorTotaisPorPapeis(totalPorPapelLista);
		
		return investimentoRepository.save(investir);
	}

	private Map<String, Double> totalPorPapelAdquirido(List<Papel> papelList) {
		Map<String, Double> totalPorPapel = papelList.stream()
			.collect(Collectors.groupingBy(Papel::getTicker, Collectors.summingDouble(Papel::getPreco)));
		return totalPorPapel;
	}

}
