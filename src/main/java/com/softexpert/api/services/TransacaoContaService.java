package com.softexpert.api.services;

import java.util.Date;
import java.util.List;

import com.softexpert.api.entities.TransacaoConta;

public interface TransacaoContaService {
	
	TransacaoConta depositar(TransacaoConta transacao);
	
	TransacaoConta sacar(TransacaoConta transacao);
	
	List<TransacaoConta> getAllBetween(Date start, Date end);
}
