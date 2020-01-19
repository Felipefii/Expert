package com.softexpert.api.services;

import java.util.List;
import java.util.Optional;

import com.softexpert.api.entities.Empresa;

public interface EmpresaService {

	List<Empresa> getAll();
	
	Empresa insert(Empresa empresa);
	
	Optional<Empresa> getOne(Long id);
	
	Empresa getByName(String name);
}
