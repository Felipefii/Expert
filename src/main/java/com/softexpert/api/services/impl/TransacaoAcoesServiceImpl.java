package com.softexpert.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softexpert.api.entities.TransacaoAcoes;
import com.softexpert.api.entities.enums.TipoTransacaoAcoesEnum;
import com.softexpert.api.repositories.TransacaoAcoesRepository;
import com.softexpert.api.services.TransacaoAcoesService;

@Service
public class TransacaoAcoesServiceImpl implements TransacaoAcoesService {

	@Autowired
	TransacaoAcoesRepository transacaoAcoesRepository;

	@Override
	public TransacaoAcoes insert(TransacaoAcoes transacao) {
		
		return transacaoAcoesRepository.saveAndFlush(transacao);
	}

	@Override
	public List<TransacaoAcoes> getAll() {
		
		return transacaoAcoesRepository.findAll();
	}

	@Override
	public List<TransacaoAcoes> getTransacoesPorTipoAndMonitoramento(TipoTransacaoAcoesEnum tipo,
			com.softexpert.api.entities.Monitoramento monitoramento) {
		
		return transacaoAcoesRepository.findByMonitoramentoAndTipoTransacaoAcoesEnum(monitoramento, tipo);
	}

	
	
	
}
