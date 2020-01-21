package com.softexpert.api.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.softexpert.api.entities.Monitoramento;
import com.softexpert.api.entities.TransacaoAcoes;
import com.softexpert.api.entities.enums.TipoTransacaoAcoesEnum;
import com.softexpert.api.services.ContaService;
import com.softexpert.api.services.MonitoramentoService;
import com.softexpert.api.services.TransacaoAcoesService;

@RunWith(SpringRunner.class)
@SpringBootTest
class TransacaoAcoesServiceTeste {

	@Autowired
	TransacaoAcoesService transacaoAcoesService;
	
	@Autowired
	ContaService contaService;
	
	@Autowired
	MonitoramentoService monitoramentoService;
	
	@Test
	void testInsert() {
		
		Date data = new Date();
		
				
		TransacaoAcoes transacao = new TransacaoAcoes();
		transacao.setDataTransacao(data);
		transacao.setQuantidade(10);
		transacao.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.COMPRA);
		transacao.setValor(20F);
		
		transacao = transacaoAcoesService.insert(transacao);
		
		assertNotNull(transacao.getId());
		assertEquals(transacao.getTipoTransacaoAcoesEnum().getDescricao(), "Compra de ações");
		assertEquals(transacao.getDataTransacao(), data);
		assertNull(transacao.getMonitoramento());
	}

	@Test
	void testGetAll() {
		Date data = new Date();
		
		List<TransacaoAcoes> transacoes = new ArrayList<TransacaoAcoes>();
		
		TransacaoAcoes transacao = new TransacaoAcoes();
		TransacaoAcoes transacao2 = new TransacaoAcoes();
		
		transacao.setDataTransacao(data);
		transacao.setQuantidade(10);
		transacao.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.COMPRA);
		transacao.setValor(20F);
		
		transacao.setDataTransacao(data);
		transacao.setQuantidade(20);
		transacao.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.VENDA);
		transacao.setValor(50F);
		
		transacao = transacaoAcoesService.insert(transacao);
		transacao2 = transacaoAcoesService.insert(transacao2);
		
		transacoes = transacaoAcoesService.getAll();
		
		assertFalse(transacoes.isEmpty());
		assertNotNull(transacoes);
		
		
	}

	@Test
	void testGetTransacoesPorTipoAndMonitoramento() {
		Date data = new Date();
		
		Monitoramento monitoramento = new Monitoramento();
		TransacaoAcoes transacao = new TransacaoAcoes();
		List<TransacaoAcoes> transacoes = new ArrayList<>();
		
		monitoramento = monitoramentoService.insert(monitoramento);
		
		transacao.setDataTransacao(data);
		transacao.setQuantidade(10);
		transacao.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.COMPRA);
		transacao.setValor(20F);
		transacao.setMonitoramento(monitoramento);
		
		transacao = transacaoAcoesService.insert(transacao);
		
		transacoes = transacaoAcoesService.getTransacoesPorTipoAndMonitoramento(TipoTransacaoAcoesEnum.COMPRA, monitoramento);
		
		assertEquals(transacoes.isEmpty(), false);
		for(TransacaoAcoes trans : transacoes) {
			assertNotNull(trans.getMonitoramento());
		}
	}

}
