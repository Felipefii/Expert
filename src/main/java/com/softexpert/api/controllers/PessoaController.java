package com.softexpert.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> getAll(){
		
		List<Pessoa> pessoas = pessoaService.getAll();
		if(pessoas.isEmpty()) throw new HomeNotFoundException("Nenhuma pessoa foi encontrada");
		
		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> insert(@RequestBody Pessoa pessoa){
		
		Pessoa pes = pessoaService.insert(pessoa);
		
		if(pes.equals(null)) throw new HomeBadRequestException("Erro ao cadastrar pessoa");
		
		return new ResponseEntity<Pessoa>(pes, HttpStatus.CREATED);
		
	}
	
}
