package com.softexpert.api.services;

import java.util.List;
import java.util.Optional;

import com.softexpert.api.entities.Pessoa;

public interface PessoaService {

	/**
	 * Metodo utilizado para buscar todas as pessoas 
	 * 
	 * @author Felipe Nazário 
	 * 
	 * 
	 * @return retorna todas as pessoas cadastradas
	 * 
	 */
	List<Pessoa> getAll();
	
	/**
	 * Metodo utilizado para buscar uma pessoa 
	 * 
	 * @author Felipe Nazário 
	 * @param id
	 * 
	 * @return retorna uma pessoa
	 * 
	 */
	Optional<Pessoa> getOne(Long id);
	
	/**
	 * Metodo utilizado para persistir uma pessoa 
	 * 
	 * @author Felipe Nazário 
	 * @param pessoa
	 * 
	 * @return retorna pessoa persistida
	 * 
	 */
	Pessoa insert(Pessoa pessoa);
}
