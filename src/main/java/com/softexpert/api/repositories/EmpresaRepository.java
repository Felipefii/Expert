package com.softexpert.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softexpert.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	Empresa findByNome(String name);
	
}
