package com.softexpert.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softexpert.api.entities.Empresa;
import com.softexpert.api.repositories.EmpresaRepository;
import com.softexpert.api.services.EmpresaService;


@Service
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	EmpresaRepository empresaRepository;
	
	@Override
	public List<Empresa> getAll() {
		
		return empresaRepository.findAll();
	}

	@Override
	public Empresa insert(Empresa empresa) {
		
		return empresaRepository.saveAndFlush(empresa);
	}

	@Override
	public Optional<Empresa> getOne(Long id) {
		
		return empresaRepository.findById(id);
	}

	@Override
	public Empresa getByName(String name) {
		
		return empresaRepository.findByNome(name);
	}

}
