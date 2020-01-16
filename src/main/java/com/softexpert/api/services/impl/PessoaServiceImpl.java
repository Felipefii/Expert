package com.softexpert.api.services.impl;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Pessoa insert(Pessoa pessoa) {
		
		return pessoaRepository.saveAndFlush(pessoa);
	}

	@Override
	public Optional<Pessoa> getOne(Long id) {
		
		return pessoaRepository.findById(id);
	}

}
