package com.softexpert.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softexpert.api.entities.Conta;
import com.softexpert.api.repositories.ContaRepository;
import com.softexpert.api.services.ContaService;

@Service
public class ContaServiceImpl implements ContaService{

	@Autowired
	ContaRepository contaRepository;
	
	@Override
	public List<Conta> getAll() {
		
		return contaRepository.findAll();
	}
	
}
