package com.softexpert.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softexpert.api.entities.Monitoramento;
import com.softexpert.api.exceptions.errors.HomeNotFoundException;
import com.softexpert.api.repositories.MonitoramentoRepository;
import com.softexpert.api.repositories.TransacaoAcoesRepository;
import com.softexpert.api.services.MonitoramentoService;

@Service
public class MonitoramentoServiceImpl implements MonitoramentoService{

	@Autowired
	MonitoramentoRepository monitoramentoRepository;
	
	@Autowired
	TransacaoAcoesRepository transacaoAcoesRepository;
	
	@Override
	public Monitoramento insert(Monitoramento monitoramento) {
		
		return monitoramentoRepository.saveAndFlush(monitoramento);
	}

	@Override
	public List<Monitoramento> getAll() {
		
		return monitoramentoRepository.findAll();
	}

	@Override
	public Optional<Monitoramento> getOne(Long id) {
		
		return monitoramentoRepository.findById(id);
	}

	@Override
	public void deleteOne(Long id) {
		Optional<Monitoramento> monitoramento = monitoramentoRepository.findById(id);
		if(!monitoramento.isPresent()) throw new HomeNotFoundException("Não foi encontrado monitoramento para excluir");
		monitoramentoRepository.delete(monitoramento.get());
	}

	
}
