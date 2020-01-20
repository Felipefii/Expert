package com.softexpert.api.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softexpert.api.entities.TransacaoConta;

public interface TransacaoContaRepository extends JpaRepository<TransacaoConta, Long>{
	
	List<TransacaoConta> findByDataTransacaoBetween(Date start, Date end); 
	
}
