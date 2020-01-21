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

import com.softexpert.api.entities.Empresa;
import com.softexpert.api.services.EmpresaService;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmpresaServiceTest {

	@Autowired
	EmpresaService empresaService;
	
	@Test
	void testGetAll() {
		List<Empresa> empresas = new ArrayList<>();
		Empresa empresa1 = new Empresa();
		Empresa empresa2 = new Empresa();
		empresa1.setNome("Empresa 1");
		empresa2.setNome("Empresa 2");
		empresaService.insert(empresa1);
		empresaService.insert(empresa2);
		
		empresas = empresaService.getAll();
		
		assertNotNull(empresas);
		assertEquals(empresas.isEmpty(), false);
		
	}

	@Test
	void testInsert() {
		Empresa empresa = new Empresa();
		empresa.setNome("Empresa");
		
		empresa = empresaService.insert(empresa);
		
		assertEquals(empresa.getNome(), "Empresa");
		assertNotNull(empresa.getId());
		assertNull(empresa.getMonitoramentos());
	}

	@Test
	void testGetOne() {
		Empresa empresa = new Empresa();
		empresa.setNome("Empresa teste");
		empresa = empresaService.insert(empresa);
		
		Optional<Empresa> empresa2 = empresaService.getOne(empresa.getId());
		assertEquals(empresa2.isPresent(), true);
		assertEquals(empresa2.get().getNome(), "Empresa teste");
	}

	@Test
	void testGetByName() {
		Empresa empresa = new Empresa();
		empresa.setNome("teste");
		empresa = empresaService.insert(empresa);
		
		Empresa empresa2 = empresaService.getByName("teste");
		assertEquals(empresa2.getNome(), "teste");
	}

}
