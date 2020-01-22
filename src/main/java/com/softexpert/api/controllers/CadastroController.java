package com.softexpert.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
	
	/**
	 * Metodo utilizado para inserir e retornar um novo cadastro de pessoa/conta
	 * 
	 * 
	 * pode ser acessado por meio do link
	 * http://localhost:8080/cadastro
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return cadastro de pessoa/conta efetuado
	 * 
	 */
	
	@PostMapping	
	public ResponseEntity<Pessoa> insertOne(@Valid @RequestBody Pessoa pessoa, BindingResult result){
		
		if(result.hasErrors()) throw new HomeBadRequestException("Alguns campos não foram preenchidos corretamente");
		
		if(pessoa.getConta().getValorCompra() > pessoa.getConta().getValorVenda()) 
			throw new HomeBadRequestException("Para maior garantia de lucro, favor, indicar valor de compra menor que o valor de venda");
		
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
