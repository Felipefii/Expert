package com.softexpert.api.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softexpert.api.services.ContaService;
import com.softexpert.api.services.TransacaoAcoesService;

@Service
public class MonitorJob {

	@Autowired
	ContaService contaService;

	@Autowired
	TransacaoAcoesService transacaoAcoesService;



}
