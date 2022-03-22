package com.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.domain.Papel;
import com.dev.domain.Status;
import com.dev.repository.PapelRepository;

@Service
public class PapelService {
	
	@Autowired
	private PapelRepository papelRepository;
	
	public List<Papel> buscarTodos(){
		return papelRepository.findAll();
	}
	
	public Optional<Papel> buscarPorId(Long id){
		return papelRepository.findById(id);
	}
	
	public List<Papel> buscarPorStatus(Status status){
		return papelRepository.findByStatus(status);
	}
	
	public Papel comprar(Papel papel) {
		return papelRepository.save(papel);
	}
	
	public Papel alterarPreco(Long papelId, Papel papel) {
		Papel papelAlteraPreco = papelRepository.findById(papelId).get();
		papelAlteraPreco.setPreco(papel.getPreco());
		return papelRepository.save(papelAlteraPreco);
	}
	
	public Papel alterarStatus(Long papelId, Papel papel) {
		Papel papelAlterarStatus = papelRepository.findById(papelId).get();
		papelAlterarStatus.setStatus(papel.getStatus());
		return papelRepository.save(papelAlterarStatus);
	}
	
	public void remover(Long papelId) {
		papelRepository.deleteById(papelId);
	}

}
