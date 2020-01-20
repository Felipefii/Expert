package com.softexpert.api.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softexpert.api.entities.TransacaoConta;
import com.softexpert.api.repositories.TransacaoContaRepository;
import com.softexpert.api.services.TransacaoContaService;

@Service
public class TransacaoContaServiceImpl implements TransacaoContaService{

	@Autowired
	TransacaoContaRepository transacaoContaRepository;
	
	@Override
	public TransacaoConta depositar(TransacaoConta transacao) {
		
		return transacaoContaRepository.saveAndFlush(transacao);
	}

	@Override
	public TransacaoConta sacar(TransacaoConta transacao) {
		
		return transacaoContaRepository.saveAndFlush(transacao);
	}

	@Override
	public List<TransacaoConta> getAllBetween(Date start, Date end) {
		
		return transacaoContaRepository.findByDataTransacaoBetween(start, end);
	}

}
