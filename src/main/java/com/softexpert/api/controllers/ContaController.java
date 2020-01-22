package com.softexpert.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
	
	/**
	 * Metodo utilizado para retornar dados sobre as contas cadastradas
	 *  
	 * pode ser acessado por meio do link
	 * http://localhost:8080/contas
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return retorna todas as contas
	 * 
	 */
	
	@GetMapping
	public ResponseEntity<List<Conta>> getAll(){
		
		List<Conta> contas = contaService.getAll();
		if(contas.isEmpty()) throw new HomeNotFoundException("Nenhuma conta encontrada");
		
		return new ResponseEntity<List<Conta>>(contas, HttpStatus.OK);
	}
	
	
	/**
	 * Metodo utilizado para atualizar campo margem e outros campos de conta
	 *  
	 * 
	 * pode ser acessado por meio do link
	 * http://localhost:8080/contas
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return retorna conta atualizada
	 * 
	 */

	@PutMapping
	public ResponseEntity<Conta> insertMargem(@Valid @RequestBody Conta conta, BindingResult result){
		
		if(result.hasErrors()) throw new HomeBadRequestException("Alguns campos não foram preenchidos corretamente");
		
		
		Conta con = contaService.insert(conta);
		if(con.equals(null)) throw new HomeBadRequestException("Erro ao cadastrar conta");
		
		return new ResponseEntity<Conta>(con, HttpStatus.CREATED);
	}
}
