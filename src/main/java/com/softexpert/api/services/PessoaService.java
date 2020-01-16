package com.softexpert.api.services;

import java.util.List;
import java.util.Optional;

import com.softexpert.api.entities.Pessoa;

public interface PessoaService {

	List<Pessoa> getAll();
	
	Optional<Pessoa> getOne(Long id);
	
	Pessoa insert(Pessoa pessoa);
}
