package com.softexpert.api.services;

import java.util.List;
import java.util.Optional;

import com.softexpert.api.entities.Empresa;

public interface EmpresaService {

	/**
	 * Metodo utilizado para buscar empresas cadastradas
	 * 
	 * @author Felipe Nazário 
	 * 
	 * 
	 * @return retorna todas as empresas
	 * 
	 */
	List<Empresa> getAll();
	
	/**
	 * Metodo utilizado para persistir uma empresa 
	 * 
	 * @author Felipe Nazário 
	 * @param empresa
	 * 
	 * @return retorna empresa cadastrada
	 * 
	 */
	Empresa insert(Empresa empresa);
	
	/**
	 * Metodo utilizado para buscar uma empresa
	 * 
	 * @author Felipe Nazário 
	 * @param id
	 * 
	 * @return retorna uma empresa
	 * 
	 */
	Optional<Empresa> getOne(Long id);
	
	
	/**
	 * Metodo utilizado para buscar empresa por nome
	 * 
	 * @author Felipe Nazário 
	 * @param nome
	 * 
	 * @return retorna uma empresa
	 * 
	 */
	Empresa getByName(String name);
}
