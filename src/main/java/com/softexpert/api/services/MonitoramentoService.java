package com.softexpert.api.services;

import java.util.List;
import java.util.Optional;

import com.softexpert.api.entities.Monitoramento;

public interface MonitoramentoService {
	/**
	 * Metodo utilizado para persistir um monitoramento 
	 * 
	 * @author Felipe Nazário 
	 * @param monitoramento
	 * 
	 * @return retorna monitoramento persistido
	 * 
	 */
	Monitoramento insert(Monitoramento monitoramento);
	
	
	/**
	 * Metodo utilizado para buscar todos os monitoramentos 
	 * 
	 * @author Felipe Nazário 
	 * 
	 * 
	 * @return retorna todos os monitoramentos
	 * 
	 */
	List<Monitoramento> getAll();
	
	
	/**
	 * Metodo utilizado para buscar um monitoramento 
	 * 
	 * @author Felipe Nazário 
	 * @param id
	 * 
	 * @return retorna um monitoramento
	 * 
	 */
	Optional<Monitoramento> getOne(Long id);
	
	/**
	 * Metodo utilizado para deletar um monitoramento 
	 * 
	 * @author Felipe Nazário 
	 * @param id
	 * 
	 * @return retorna void
	 * 
	 */
	void deleteOne(Long id);
}
