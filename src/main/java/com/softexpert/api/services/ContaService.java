package com.softexpert.api.services;

import java.util.List;
import java.util.Optional;

import com.softexpert.api.entities.Conta;

public interface ContaService {

	/**
	 * Metodo utilizado para buscar contas 
	 * 
	 * @author Felipe Nazário 
	 * 
	 * 
	 * @return retorna todas as contas
	 * 
	 */
	List<Conta> getAll();
	
	/**
	 * Metodo utilizado para persistir uma nova conta 
	 * 
	 * @author Felipe Nazário 
	 * @param conta
	 * 
	 * @return retorna uma conta cadastrada
	 * 
	 */	
	Conta insert(Conta conta);
	
	/**
	 * Metodo utilizado para buscar uma conta
	 * 
	 * @author Felipe Nazário 
	 * @param id
	 * 
	 * @return retorna uma conta
	 * 
	 */
	Optional<Conta> getOne(Long id);
}
