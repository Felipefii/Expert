package com.softexpert.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping	
	public ResponseEntity<Pessoa> insertOne(@RequestBody Pessoa pessoa){
		Conta conta = new Conta(); 
		pessoa.getConta().setSaldo(0D);
		pessoa.getConta().setQtdeAcoes(0);
		conta = contaService.insert(pessoa.getConta());
		
		if(conta.equals(null)) throw new HomeBadRequestException("Erro ao criar nova conta.");
		
		pessoa = pessoaService.insert(pessoa);
		
		if(pessoa.equals(null)) throw new HomeBadRequestException("Erro ao cadastrar pessoa");
		
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.CREATED);
		
		
	}
}
