package com.softexpert.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softexpert.api.entities.Pessoa;
import com.softexpert.api.repositories.PessoaRepository;
import com.softexpert.api.services.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;
	
	@Override
	public List<Pessoa> getAll() {
		
		return pessoaRepository.findAll();
	}

}