package com.softexpert.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softexpert.api.entities.Monitoramento;
import com.softexpert.api.exceptions.errors.HomeNotFoundException;
import com.softexpert.api.services.MonitorService;
import com.softexpert.api.services.MonitoramentoService;

@RestController
@RequestMapping("/monitor")
public class MonitorController {

	@Autowired
	MonitorService monitorService;

	@Autowired
	MonitoramentoService monitoramentoService;
	
	@GetMapping("/{id_conta}/{id_empresa}")
	public void monitorar(@PathVariable(value="id_conta") Long idConta, @PathVariable(value="id_empresa") Long idEmpresa) {
		
		monitorService.monitorar(idEmpresa, idConta);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Monitoramento>> getAll() {
		List<Monitoramento> monitoramentos = monitoramentoService.getAll();
		if(monitoramentos.isEmpty()) throw new HomeNotFoundException("Empresas não foram encontradas");
		
		return new ResponseEntity<List<Monitoramento>>(monitoramentos, HttpStatus.OK);
	}
}
