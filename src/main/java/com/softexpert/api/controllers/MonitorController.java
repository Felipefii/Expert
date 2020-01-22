package com.softexpert.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softexpert.api.entities.Monitoramento;
import com.softexpert.api.exceptions.errors.HomeNotFoundException;
import com.softexpert.api.services.MonitorService;
import com.softexpert.api.services.MonitoramentoService;

@RestController
@RequestMapping("/monitor")
public class MonitorController {

	@Autowired
	MonitorService monitorService;

	@Autowired
	MonitoramentoService monitoramentoService;
	
	/**
	 * Metodo utilizado para inserir um novo monitoramento
	 * 
	 * 
	 * pode ser acessado por meio do link
	 * http://localhost:8080/monitor
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return retorna void
	 * 
	 */
	
	@GetMapping("/{id_conta}/{id_empresa}")
	public void monitorar(@PathVariable(value="id_conta") Long idConta, @PathVariable(value="id_empresa") Long idEmpresa) {
		
		monitorService.monitorar(idEmpresa, idConta);
		
	}
	
	/**
	 * Metodo utilizado para retornar dados de monitoramentos efetuados
	 * 
	 * 
	 * pode ser acessado por meio do link
	 * http://localhost:8080/monitor
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return retorna todos os monitoramentos
	 * 
	 */
	@GetMapping
	public ResponseEntity<List<Monitoramento>> getAll() {
		List<Monitoramento> monitoramentos = monitoramentoService.getAll();
		if(monitoramentos.isEmpty()) throw new HomeNotFoundException("Empresas não foram encontradas.");
		
		return new ResponseEntity<List<Monitoramento>>(monitoramentos, HttpStatus.OK);
	}
	
	/**
	 * Metodo utilizado para retornar dados de um monitoramento específico
	 * 
	 * 
	 * pode ser acessado por meio do link
	 * http://localhost:8080/monitor
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return retorna monitoramento
	 * 
	 */
	
	@GetMapping("/{id}")
	public ResponseEntity<Monitoramento> getOne(@PathVariable(value="id") Long id){

		Optional<Monitoramento> monitoramento = monitoramentoService.getOne(id);
		if(!monitoramento.isPresent()) throw new HomeNotFoundException("Não foi encontrado um monitoramento.");
		
		return new ResponseEntity<Monitoramento>(monitoramento.get(), HttpStatus.OK);
	}
	
	
	/**
	 * Metodo utilizado para deletar monitoramento específico
	 *  
	 * 
	 * pode ser acessado por meio do link
	 * http://localhost:8080/monitor
	 * 
	 * @author Felipe Nazário 
	 * 
	 * @return monitoramento deletado
	 * 
	 */
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOne(@PathVariable(value="id") Long id){

		Optional<Monitoramento> monitoramento = monitoramentoService.getOne(id);
		if(!monitoramento.isPresent()) throw new HomeNotFoundException("Não foi encontrado um monitoramento para deletar.");
		
		monitoramentoService.deleteOne(id);
		
		return new ResponseEntity<String>("Monitoramento deletado com sucesso", HttpStatus.OK);
	}
	
}
