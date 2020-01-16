package com.softexpert.api.services;

import java.util.List;
import java.util.Optional;

import com.softexpert.api.entities.Conta;

public interface ContaService {

	List<Conta> getAll();
	
	Conta insert(Conta conta);
	
	Optional<Conta> getOne(Long id);
}
