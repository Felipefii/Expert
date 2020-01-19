package com.softexpert.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softexpert.api.services.MonitorService;

@RestController
@RequestMapping("/monitor")
public class MonitorController {

	@Autowired
	MonitorService monitorService;
	
	@GetMapping("/{id_conta}/{id_empresa}")
	public void monitorar(@PathVariable(value="id_conta") Long idConta, @PathVariable(value="id_empresa") Long idEmpresa) {
		
		monitorService.monitorar(idEmpresa, idConta);
		
	}
}
