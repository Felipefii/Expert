package com.softexpert.api.services;

import java.util.List;

import com.softexpert.api.entities.TransacaoAcoes;

public interface TransacaoAcoesService {

	TransacaoAcoes insert(TransacaoAcoes transacao);
	
	List<TransacaoAcoes> getAll();
	
}
