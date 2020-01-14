package com.softexpert.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softexpert.api.entities.Conta;
import com.softexpert.api.exceptions.errors.HomeNotFoundException;
import com.softexpert.api.services.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	ContaService contaService;
	
	@GetMapping
	public ResponseEntity<List<Conta>> getAll(){
		
		List<Conta> contas = contaService.getAll();
		if(contas.isEmpty()) throw new HomeNotFoundException("Nenhuma conta encontrada");
		
		return new ResponseEntity<List<Conta>>(contas, HttpStatus.OK);
	}
	
}
