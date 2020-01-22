package com.softexpert.api.services;

import java.util.List;

import com.softexpert.api.entities.Monitoramento;
import com.softexpert.api.entities.TransacaoAcoes;
import com.softexpert.api.entities.enums.TipoTransacaoAcoesEnum;

public interface TransacaoAcoesService {

	/**
	 * Metodo utilizado para persistir uma transacao 
	 * 
	 * @author Felipe Nazário 
	 * @param transacao
	 * 
	 * @return retorna transação persistida
	 * 
	 */
	TransacaoAcoes insert(TransacaoAcoes transacao);
	
	/**
	 * Metodo utilizado para buscar todas as transações 
	 * 
	 * @author Felipe Nazário 
	 * 
	 * 
	 * @return retorna todas as transações
	 * 
	 */
	List<TransacaoAcoes> getAll();
	
	/**
	 * Metodo utilizado para buscar transações 
	 * filtrando por tipo de transação e monitoramento
	 * 
	 * @author Felipe Nazário 
	 * @param tipo
	 * @param monitoramento
	 * 
	 * @return retorna lista de transações
	 * 
	 */
	List<TransacaoAcoes> getTransacoesPorTipoAndMonitoramento(TipoTransacaoAcoesEnum tipo, Monitoramento monitoramento);
	
}
