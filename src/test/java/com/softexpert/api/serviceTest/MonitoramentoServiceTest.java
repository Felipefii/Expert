package com.softexpert.api.serviceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.softexpert.api.entities.Conta;
import com.softexpert.api.entities.Empresa;
import com.softexpert.api.entities.Monitoramento;
import com.softexpert.api.entities.Pessoa;
import com.softexpert.api.entities.TransacaoAcoes;
import com.softexpert.api.entities.enums.TipoTransacaoAcoesEnum;
import com.softexpert.api.services.ContaService;
import com.softexpert.api.services.EmpresaService;
import com.softexpert.api.services.MonitoramentoService;
import com.softexpert.api.services.PessoaService;
import com.softexpert.api.services.TransacaoAcoesService;

@RunWith(SpringRunner.class)
@SpringBootTest
class MonitoramentoServiceTest {

	@Autowired
	MonitoramentoService monitoramentoService;
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	ContaService contaService;
	
	@Autowired
	EmpresaService empresaService;
	
	@Autowired
	TransacaoAcoesService transacaoAcoesService;
	
	@Test
	void testInsert() {
		Monitoramento monitoramento = new Monitoramento();
		Conta conta = new Conta();		
		Pessoa pessoa = new Pessoa();
		Empresa empresa = new Empresa();
		TransacaoAcoes transacaoAcoes = new TransacaoAcoes();
		List<TransacaoAcoes> transacoes = new ArrayList<TransacaoAcoes>();
		
		pessoa.setEmail("email@email.com");
		pessoa = pessoaService.insert(pessoa);
		
		empresa.setNome("teste");
		empresa = empresaService.insert(empresa);
		
		conta.setMargemTranscoes(0.5F);
		conta.setPessoa(pessoa);
		conta.setQtdeAcoes(0);
		conta.setSaldo(1000D);
		conta.setValorCompra(10F);
		conta.setValorVenda(11F);
		
		conta = contaService.insert(conta);
		
		transacaoAcoes.setDataTransacao(new Date());
		transacaoAcoes.setValor(1000F);
		transacaoAcoes.setQuantidade(5);
		transacaoAcoes.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.COMPRA);
		
		transacaoAcoes = transacaoAcoesService.insert(transacaoAcoes);
		transacoes.add(transacaoAcoes);
		
		monitoramento.setConta(conta);
		monitoramento.setEmpresa(empresa);
		monitoramento.setTransacao(transacoes);
		
		monitoramento = monitoramentoService.insert(monitoramento);
		
		assertNotNull(monitoramento.getId());
		assertNotNull(monitoramento.getConta());
		assertNotNull(monitoramento.getEmpresa());
		
		
		
		
	}

	@Test
	void testGetAll() {
		Monitoramento monitoramento = new Monitoramento();
		Conta conta = new Conta();		
		Pessoa pessoa = new Pessoa();
		Empresa empresa = new Empresa();
		TransacaoAcoes transacaoAcoes = new TransacaoAcoes();
		List<TransacaoAcoes> transacoes = new ArrayList<TransacaoAcoes>();
		
		pessoa.setEmail("email@email.com");
		pessoa = pessoaService.insert(pessoa);
		
		empresa.setNome("teste");
		empresa = empresaService.insert(empresa);
		
		conta.setMargemTranscoes(0.5F);
		conta.setPessoa(pessoa);
		conta.setQtdeAcoes(0);
		conta.setSaldo(1000D);
		conta.setValorCompra(10F);
		conta.setValorVenda(11F);
		
		conta = contaService.insert(conta);
		
		transacaoAcoes.setDataTransacao(new Date());
		transacaoAcoes.setValor(1000F);
		transacaoAcoes.setQuantidade(5);
		transacaoAcoes.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.COMPRA);
		
		transacaoAcoes = transacaoAcoesService.insert(transacaoAcoes);
		transacoes.add(transacaoAcoes);
		
		monitoramento.setConta(conta);
		monitoramento.setEmpresa(empresa);
		monitoramento.setTransacao(transacoes);
		
		monitoramento = monitoramentoService.insert(monitoramento);
		
		List<Monitoramento> monitoramentos = monitoramentoService.getAll();
		
		assertNotNull(monitoramento.getId());
		assertNotNull(monitoramento.getConta());
		assertNotNull(monitoramento.getEmpresa());
		
		assertFalse(monitoramentos.isEmpty());
	}

	@Test
	void testGetOne() {
		Monitoramento monitoramento = new Monitoramento();
		Conta conta = new Conta();		
		Pessoa pessoa = new Pessoa();
		Empresa empresa = new Empresa();
		TransacaoAcoes transacaoAcoes = new TransacaoAcoes();
		List<TransacaoAcoes> transacoes = new ArrayList<TransacaoAcoes>();
		
		pessoa.setEmail("email@email.com");
		pessoa = pessoaService.insert(pessoa);
		
		empresa.setNome("teste");
		empresa = empresaService.insert(empresa);
		
		conta.setMargemTranscoes(0.5F);
		conta.setPessoa(pessoa);
		conta.setQtdeAcoes(0);
		conta.setSaldo(1000D);
		conta.setValorCompra(10F);
		conta.setValorVenda(11F);
		
		conta = contaService.insert(conta);
		
		transacaoAcoes.setDataTransacao(new Date());
		transacaoAcoes.setValor(1000F);
		transacaoAcoes.setQuantidade(5);
		transacaoAcoes.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.COMPRA);
		
		transacaoAcoes = transacaoAcoesService.insert(transacaoAcoes);
		transacoes.add(transacaoAcoes);
		
		monitoramento.setConta(conta);
		monitoramento.setEmpresa(empresa);
		monitoramento.setTransacao(transacoes);
		
		monitoramento = monitoramentoService.insert(monitoramento);
		
		Optional<Monitoramento> monitoramento2 = monitoramentoService.getOne(monitoramento.getId());
		
		assertNotNull(monitoramento.getId());
		assertNotNull(monitoramento.getConta());
		assertNotNull(monitoramento.getEmpresa());
		
		assertTrue(monitoramento2.isPresent());
	}

	@Test
	void testDeleteOne() {
		Monitoramento monitoramento = new Monitoramento();
		Conta conta = new Conta();		
		Pessoa pessoa = new Pessoa();
		Empresa empresa = new Empresa();
		TransacaoAcoes transacaoAcoes = new TransacaoAcoes();
		List<TransacaoAcoes> transacoes = new ArrayList<TransacaoAcoes>();
		
		pessoa.setEmail("email@email.com");
		pessoa = pessoaService.insert(pessoa);
		
		empresa.setNome("teste");
		empresa = empresaService.insert(empresa);
		
		conta.setMargemTranscoes(0.5F);
		conta.setPessoa(pessoa);
		conta.setQtdeAcoes(0);
		conta.setSaldo(1000D);
		conta.setValorCompra(10F);
		conta.setValorVenda(11F);
		
		conta = contaService.insert(conta);
		
		transacaoAcoes.setDataTransacao(new Date());
		transacaoAcoes.setValor(1000F);
		transacaoAcoes.setQuantidade(5);
		transacaoAcoes.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.COMPRA);
		
		transacaoAcoes = transacaoAcoesService.insert(transacaoAcoes);
		transacoes.add(transacaoAcoes);
		
		monitoramento.setConta(conta);
		monitoramento.setEmpresa(empresa);
		monitoramento.setTransacao(transacoes);
		
		monitoramento = monitoramentoService.insert(monitoramento);
		
		monitoramentoService.deleteOne(monitoramento.getId());
		
		Optional<Monitoramento> monitoramento2 = monitoramentoService.getOne(monitoramento.getId());
		
		assertFalse(monitoramento2.isPresent());
	}

}
