package com.softexpert.api.services;

import com.softexpert.api.entities.TransacaoConta;

public interface TransacaoContaService {
	
	TransacaoConta depositar(TransacaoConta transacao);
	
	TransacaoConta sacar(TransacaoConta transacao);
}
