package com.softexpert.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softexpert.api.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
