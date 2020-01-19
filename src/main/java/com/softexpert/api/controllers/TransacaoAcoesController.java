package com.softexpert.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softexpert.api.entities.TransacaoAcoes;
import com.softexpert.api.exceptions.errors.HomeNotFoundException;
import com.softexpert.api.services.TransacaoAcoesService;

@RestController
@RequestMapping("/acoes")
public class TransacaoAcoesController {

	@Autowired
	TransacaoAcoesService transacaoAcoesService;
	
	
	@GetMapping
	public ResponseEntity<List<TransacaoAcoes>> getAll(){
		
		List<TransacaoAcoes> acoes = transacaoAcoesService.getAll();
		if(acoes.isEmpty()) throw new HomeNotFoundException("Nenhuma transacação de ações foi encontrada");
		
		return new ResponseEntity<List<TransacaoAcoes>>(acoes, HttpStatus.OK);
	}
}
