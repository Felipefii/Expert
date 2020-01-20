package com.softexpert.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softexpert.api.entities.Monitoramento;
import com.softexpert.api.entities.TransacaoAcoes;
import com.softexpert.api.entities.enums.TipoTransacaoAcoesEnum;

public interface TransacaoAcoesRepository extends JpaRepository<TransacaoAcoes, Long>{

	List<TransacaoAcoes> findByMonitoramentoAndTipoTransacaoAcoesEnum(Monitoramento monitoramento, TipoTransacaoAcoesEnum tipo);	
	
}
