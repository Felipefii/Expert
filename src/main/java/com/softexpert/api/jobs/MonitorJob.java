package com.softexpert.api.jobs;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.softexpert.api.entities.Conta;
import com.softexpert.api.entities.TransacaoAcoes;
import com.softexpert.api.entities.enums.TipoTransacaoAcoesEnum;
import com.softexpert.api.services.ContaService;
import com.softexpert.api.services.TransacaoAcoesService;


public class MonitorJob implements Job {

	@Autowired
	ContaService contaService;
	
	@Autowired
	TransacaoAcoesService transacaoAcoesService;
	
	private List<Conta> contas;
	private static final Logger log = LoggerFactory.getLogger(MonitorJob.class);
	private Double precoAtual;
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
	
		gerarValor();
		
	}
	
	public void gerarValor(){
		
		Random random = new Random();
		log.info("Gerando valores de Compra/Venda");
		
		TransacaoAcoes transacao = new TransacaoAcoes();
		contas = contaService.getAll();
		
		for(Conta c : contas) {
			if(random.nextBoolean()) {
				this.precoAtual = c.getValorCompra() - c.getValorCompra()*0.1F*random.nextDouble();
			}else {
				this.precoAtual = c.getValorCompra() + c.getValorCompra()*0.1F*random.nextDouble();
			}
			
			if(precoAtual <= c.getValorCompra()) {
				transacao.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.COMPRA);
			}else {
				transacao.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.VENDA);
			}
			
			transacao.setConta(c);
			transacao.setValor(precoAtual);
			transacao.setDataTransacao(new Date());
			
			Integer qtde = (int) (c.getSaldo()/precoAtual);
			transacao.setQuantidade(qtde);
		}
		
	}
}
