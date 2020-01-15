package com.softexpert.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softexpert.api.entities.Conta;
import com.softexpert.api.entities.Pessoa;
import com.softexpert.api.exceptions.errors.HomeBadRequestException;
import com.softexpert.api.services.ContaService;
import com.softexpert.api.services.PessoaService;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	ContaService contaService;
	
	@PutMapping
	public ResponseEntity<Pessoa> insertOne(@RequestBody String email){
	
		Pessoa pessoa = new Pessoa();
		Conta conta = new Conta();
		
		conta = contaService.insert(conta);
		
		if(conta.equals(null)) throw new HomeBadRequestException("Erro ao criar nova conta.");
		
		pessoa.setConta(conta);
		pessoa.setEmail(email);
		
		pessoa = pessoaService.insert(pessoa);
		
		if(pessoa.equals(null)) throw new HomeBadRequestException("Erro ao cadastrar pessoa");
		
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.CREATED);
		
		
	}
}
