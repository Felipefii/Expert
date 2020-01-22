package com.softexpert.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softexpert.api.entities.Pessoa;
import com.softexpert.api.exceptions.errors.HomeBadRequestException;
import com.softexpert.api.exceptions.errors.HomeNotFoundException;
import com.softexpert.api.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaService pessoaService;
	
	/**
	 * Metodo utilizado para retornar todas as pessoas cadastradas
	 * 
	 * 
	 * pode ser acessado por meio do link
	 * http://localhost:8080/pessoas
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return retorna todas as pessoas
	 * 
	 */
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> getAll(){
		
		List<Pessoa> pessoas = pessoaService.getAll();
		if(pessoas.isEmpty()) throw new HomeNotFoundException("Nenhuma pessoa foi encontrada");
		
		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}
	
	/**
	 * Metodo utilizado para inserir pessoa
	 *  
	 * 
	 * pode ser acessado por meio do link
	 * http://localhost:8080/pessoas
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return retorna pessoa inserida
	 * 
	 */
	
	@PostMapping
	public ResponseEntity<Pessoa> insert(@Valid @RequestBody Pessoa pessoa, BindingResult result){
		
		if(result.hasErrors()) throw new HomeBadRequestException("Campo email deve ser preenchido");
		
		Pessoa pes = pessoaService.insert(pessoa);
		
		if(pes.equals(null)) throw new HomeBadRequestException("Erro ao cadastrar pessoa");
		
		return new ResponseEntity<Pessoa>(pes, HttpStatus.CREATED);
		
	}
	
}
