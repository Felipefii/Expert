package com.softexpert.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softexpert.api.entities.Conta;
import com.softexpert.api.exceptions.errors.HomeBadRequestException;
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

	@PutMapping
	public ResponseEntity<Conta> insert(@RequestBody Conta conta){
		Conta con = contaService.insert(conta);
		if(con.equals(null)) throw new HomeBadRequestException("Erro ao cadastrar conta");
		
		return new ResponseEntity<Conta>(con, HttpStatus.CREATED);
	}
}
