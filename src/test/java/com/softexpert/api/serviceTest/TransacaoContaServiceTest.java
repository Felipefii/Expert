package com.softexpert.api.serviceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.softexpert.api.entities.Conta;
import com.softexpert.api.entities.TransacaoConta;
import com.softexpert.api.entities.enums.TipoTransacaoContaEnum;
import com.softexpert.api.services.ContaService;
import com.softexpert.api.services.TransacaoContaService;

@RunWith(SpringRunner.class)
@SpringBootTest
class TransacaoContaServiceTest {

	@Autowired
	TransacaoContaService transacaoContaService;
	
	@Autowired
	ContaService contaService;
	
	@Test
	void testDepositar() {
		Conta conta = new Conta();
		Date data = new Date();
		conta = contaService.insert(conta);
		TransacaoConta transacao = new TransacaoConta();
		
		transacao.setConta(conta);
		transacao.setDataTransacao(data);
		transacao.setValor(10D);
		transacao.setMoeda("BRL");
		transacao.setTipoTransacaoContaEnum(TipoTransacaoContaEnum.DEPOSITO);
		
		transacao = transacaoContaService.depositar(transacao);
		
		assertNotNull(transacao.getId());
		assertNotNull(transacao.getConta());
	}


	@Test
	void testGetAllBetween() {
		Conta conta = new Conta();
		List<TransacaoConta> transacoes = new ArrayList<>();
		Date start = new Date();
		conta = contaService.insert(conta);
		TransacaoConta transacao = new TransacaoConta();
		
		transacao.setConta(conta);
		transacao.setDataTransacao(start);
		transacao.setValor(10D);
		transacao.setMoeda("BRL");
		transacao.setTipoTransacaoContaEnum(TipoTransacaoContaEnum.DEPOSITO);
		
		transacao = transacaoContaService.depositar(transacao);
		
		transacoes = transacaoContaService.getAllBetween(start, new Date());
		
		assertFalse(transacoes.isEmpty());
		assertFalse(transacoes.equals(null));
		
		
	}

}
