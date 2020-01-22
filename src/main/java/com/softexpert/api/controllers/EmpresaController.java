package com.softexpert.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softexpert.api.entities.Empresa;
import com.softexpert.api.exceptions.errors.HomeBadRequestException;
import com.softexpert.api.exceptions.errors.HomeNotFoundException;
import com.softexpert.api.services.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

	@Autowired
	EmpresaService empresaService;
	
	/**
	 * Metodo utilizado para retornar empresas cadastradas
	 * 
	 * 
	 * pode ser acessado por meio do link
	 * http://localhost:8080/empresas
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return retorna todos as empresas
	 * 
	 */
	
	@GetMapping
	public ResponseEntity<List<Empresa>> getAll(){
		List<Empresa> empresas = empresaService.getAll();
		if(empresas.isEmpty()) throw new HomeNotFoundException("Nenhuma empresa foi encontrada");
		
		return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);
	}
	
	
	/**
	 * Metodo utilizado para inserir empresa 
	 * 
	 * pode ser acessado por meio do link
	 * http://localhost:8080/empresas
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return retorna empresa inserida
	 * 
	 */
	
	@PostMapping
	public ResponseEntity<Empresa> insert(@Valid @RequestBody Empresa empresa, BindingResult result){
		
		if(result.hasErrors()) throw new HomeBadRequestException("Nome da empresa deve ser preenchido");
		
		Empresa emp = empresaService.insert(empresa);
		if(emp.equals(null)) throw new HomeBadRequestException("Não foi possível cadastrar a empresa");
		
		return new ResponseEntity<Empresa>(emp, HttpStatus.CREATED);
	}
	
	/**
	 * Metodo utilizado para retornar dados de uma empresa específica 
	 *  
	 * 
	 * pode ser acessado por meio do link
	 * http://localhost:8080/empresas
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return retorna empresa inserida
	 * 
	 */
	
	@GetMapping("/{id}")
	public ResponseEntity<Empresa> getOne(@PathVariable(value="id") Long id){
		Optional<Empresa> empresa = empresaService.getOne(id);
		if(!empresa.isPresent()) throw new HomeNotFoundException("Não foi encontrado uma empresa");
		
		return new ResponseEntity<Empresa>(empresa.get(),HttpStatus.OK);
	}
}
