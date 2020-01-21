package com.softexpert.api.serviceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.softexpert.api.entities.Pessoa;
import com.softexpert.api.services.PessoaService;

@RunWith(SpringRunner.class)
@SpringBootTest
class PessoaServiceTest {

	@Autowired
	PessoaService pessoaService;
	
	@Test
	void testGetAll() {
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa pessoa = new Pessoa();
		Pessoa pessoa2 = new Pessoa();
		pessoa.setEmail("email@email.com");
		pessoaService.insert(pessoa);
		pessoaService.insert(pessoa2);
		
		pessoas = pessoaService.getAll();
		
		assertEquals(pessoas.isEmpty(), false);
		assertNotNull(pessoas);
		
	}

	@Test
	void testInsert() {
		Pessoa pessoa = new Pessoa();
		pessoa.setEmail("email@email.com");
		
		pessoa = pessoaService.insert(pessoa);
		
		assertNotNull(pessoa.getId());
		assertEquals(pessoa.getEmail(), "email@email.com");
	}

	@Test
	void testGetOne() {
		Pessoa pessoa = new Pessoa();
		
		pessoa.setEmail("email@email.com");
		
		pessoa = pessoaService.insert(pessoa);
		
		Optional<Pessoa> pessoa2= pessoaService.getOne(pessoa.getId());
		
		assertEquals(pessoa2.isPresent(), true);
		assertEquals(pessoa.getEmail(), "email@email.com");
	}

}
