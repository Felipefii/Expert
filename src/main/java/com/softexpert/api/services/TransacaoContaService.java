package com.softexpert.api.services;

import java.util.Date;
import java.util.List;

import com.softexpert.api.entities.TransacaoConta;

public interface TransacaoContaService {
	
	/**
	 * Metodo utilizado para persistir um depósito 
	 * 
	 * @author Felipe Nazário 
	 * @param transacao
	 * 
	 * @return retorna depósito persistido
	 * 
	 */
	TransacaoConta depositar(TransacaoConta transacao);
	
	
	/**
	 * Metodo utilizado para persistir um saque 
	 * 
	 * @author Felipe Nazário 
	 * @param transacao
	 * 
	 * @return retorna saque persistido
	 * 
	 */
	TransacaoConta sacar(TransacaoConta transacao);
	
	/**
	 * Metodo utilizado para buscar transações em um período de tempo
	 * 
	 * @author Felipe Nazário 
	 * @param data inicial
	 * @param data final
	 * 
	 * @return retorna lista de transações
	 * 
	 */
	List<TransacaoConta> getAllBetween(Date start, Date end);
}
