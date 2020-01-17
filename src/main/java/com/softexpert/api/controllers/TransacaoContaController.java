package com.softexpert.api.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softexpert.api.entities.Conta;
import com.softexpert.api.entities.TransacaoConta;
import com.softexpert.api.entities.enums.TipoTransacaoContaEnum;
import com.softexpert.api.exceptions.errors.HomeBadRequestException;
import com.softexpert.api.exceptions.errors.HomeNotFoundException;
import com.softexpert.api.services.ContaService;
import com.softexpert.api.services.TransacaoContaService;

@RestController
@RequestMapping("/transacao")
public class TransacaoContaController {

	@Autowired
	TransacaoContaService transacaoContaService;
	
	@Autowired
	ContaService contaService;
	
	@PostMapping("/deposito/{id}")
	public ResponseEntity<TransacaoConta> depositar(@RequestBody TransacaoConta tConta, @PathVariable(value="id") Long id) {
		
		
		if(tConta.getValor() <= 0 || tConta.getValor().equals(null)) throw new HomeBadRequestException("O valor para depósito deve ser maior que 0 (zero)");
		
		Optional<Conta> conta = contaService.getOne(id);
		
		if(!conta.isPresent()) throw new HomeNotFoundException("Conta para depósito não encontrada");
		
		if(!tConta.getMoeda().equals("BRL")) {
						
			/*
			 * HttpResponse<JsonNode> res =
			 * kong.unirest.Unirest.get("https://economia.awesomeapi.com.br/all/USD-BRL")
			 * .asObject(JsonNode.class);
			 */
		
		}
		
		tConta.setTipoTransacaoContaEnum(TipoTransacaoContaEnum.DEPOSITO);
		tConta.setDataTransacao(new Date());
		tConta.setConta(conta.get());
		
		TransacaoConta transConta = transacaoContaService.depositar(tConta);
		
		if(transConta.equals(null)) throw new HomeBadRequestException("Não foi possivel efetuar o depósito");
		
		if(conta.get().getSaldo().equals(null)) conta.get().setSaldo(0D);
		
		conta.get().setSaldo(conta.get().getSaldo() + tConta.getValor());
		
		contaService.insert(conta.get());
		
		return new ResponseEntity<TransacaoConta>(transConta, HttpStatus.CREATED);
	}
	
	@PostMapping("/saque/{id}")
	public ResponseEntity<TransacaoConta> sacar(@RequestBody TransacaoConta tConta, @PathVariable(value="id") Long id){
		
		Optional<Conta> conta = contaService.getOne(id);
		if(!conta.isPresent()) throw new HomeNotFoundException("Conta para saque não encontrada");
		if(tConta.getValor() > conta.get().getSaldo()) throw new HomeBadRequestException("Saldo insuficiente para saque");
		
		tConta.setTipoTransacaoContaEnum(TipoTransacaoContaEnum.SAQUE);
		tConta.setDataTransacao(new Date());
		tConta.setConta(conta.get());
		
		TransacaoConta transConta = transacaoContaService.sacar(tConta);
		
		conta.get().setSaldo(conta.get().getSaldo() - tConta.getValor());
		
		contaService.insert(conta.get());
		
		return new ResponseEntity<TransacaoConta>(transConta, HttpStatus.CREATED);
	}
	
}
