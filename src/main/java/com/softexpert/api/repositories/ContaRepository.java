package com.softexpert.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softexpert.api.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

}
