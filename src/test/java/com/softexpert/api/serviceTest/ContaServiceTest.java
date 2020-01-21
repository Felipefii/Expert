package com.softexpert.api.serviceTest;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.softexpert.api.entities.Conta;
import com.softexpert.api.entities.Pessoa;
import com.softexpert.api.services.ContaService;
import com.softexpert.api.services.PessoaService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ContaServiceTest {

	@Autowired
	ContaService contaService;
	
	@Autowired
	PessoaService pessoaService;
	
	@Test
	void testGetAll() {
		Conta conta = new Conta();
		Conta conta2 = new Conta();
		
		Pessoa pessoa = new Pessoa();
		Pessoa pessoa2 = new Pessoa();
		
		pessoa.setEmail("email@email.com");
		pessoa2.setEmail("email2@email.com");
		
		pessoa = pessoaService.insert(pessoa);
		pessoa2 = pessoaService.insert(pessoa2);
		
		conta.setMargemTranscoes(0.5F);
		conta.setPessoa(pessoa);
		conta.setQtdeAcoes(0);
		conta.setSaldo(1000D);
		conta.setValorCompra(10F);
		conta.setValorVenda(11F);
		
		conta2.setMargemTranscoes(0.5F);
		conta2.setPessoa(pessoa);
		conta2.setQtdeAcoes(0);
		conta2.setSaldo(1000D);
		conta2.setValorCompra(10F);
		conta2.setValorVenda(11F);
		
		conta = contaService.insert(conta);
		conta2 = contaService.insert(conta2);
		
		List<Conta> contas = contaService.getAll();
		
		assertFalse(contas.isEmpty());
	}

	@Test
	void testInsert() {
		Conta conta = new Conta();
		Pessoa pessoa = new Pessoa();
		
		pessoa.setEmail("email@email.com");
		pessoa = pessoaService.insert(pessoa);
		
		conta.setMargemTranscoes(0.5F);
		conta.setPessoa(pessoa);
		conta.setQtdeAcoes(0);
		conta.setSaldo(1000D);
		conta.setValorCompra(10F);
		conta.setValorVenda(11F);
		
		conta = contaService.insert(conta);
		
		assertNotNull(conta.getId());
		assertEquals(conta.getSaldo(), 1000);
	}

	@Test
	void testGetOne() {
		Conta conta = new Conta();
		
		Pessoa pessoa = new Pessoa();
		
		pessoa.setEmail("email@email.com");
		pessoa = pessoaService.insert(pessoa);
		
		conta.setMargemTranscoes(0.5F);
		conta.setPessoa(pessoa);
		conta.setQtdeAcoes(0);
		conta.setSaldo(1000D);
		conta.setValorCompra(10F);
		conta.setValorVenda(11F);
		
		conta = contaService.insert(conta);
		
		Optional<Conta> conta2 = contaService.getOne(conta.getId());
		
		assertTrue(conta2.isPresent());
		assertEquals(conta2.get().getMargemTranscoes(), 0.5F);
		
	}

}
