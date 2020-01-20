package com.softexpert.api.services;

import java.util.List;

import com.softexpert.api.entities.Monitoramento;
import com.softexpert.api.entities.TransacaoAcoes;
import com.softexpert.api.entities.enums.TipoTransacaoAcoesEnum;

public interface TransacaoAcoesService {

	TransacaoAcoes insert(TransacaoAcoes transacao);
	
	List<TransacaoAcoes> getAll();
	
	List<TransacaoAcoes> getTransacoesPorTipoAndMonitoramento(TipoTransacaoAcoesEnum tipo, Monitoramento monitoramento);
	
}
