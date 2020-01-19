package com.softexpert.api.services;

import java.util.List;
import java.util.Optional;

import com.softexpert.api.entities.Monitoramento;

public interface MonitoramentoService {

	Monitoramento insert(Monitoramento monitoramento);
	
	List<Monitoramento> getAll();
	
	Optional<Monitoramento> getOne(Long id);
	
	void deleteOne(Long id);
}
