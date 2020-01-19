package com.softexpert.api.services;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softexpert.api.entities.Conta;
import com.softexpert.api.entities.Empresa;
import com.softexpert.api.entities.Monitoramento;
import com.softexpert.api.entities.TransacaoAcoes;
import com.softexpert.api.entities.enums.TipoTransacaoAcoesEnum;
import com.softexpert.api.exceptions.errors.HomeNotFoundException;

@Service
public class MonitorService {

	@Autowired
	ContaService contaService;

	@Autowired
	TransacaoAcoesService transacaoAcoesService;

	@Autowired
	EmpresaService empresaService;
	
	@Autowired 
	MonitoramentoService monitoramentoService;

	public void monitorar(Long idEmpresa, Long idConta) {
		
		new Thread(() ->{
			
			Monitoramento monitoramento = new Monitoramento();
			
			Conta conta = contaService.getOne(idConta).get();
			if(conta.equals(null)) throw new HomeNotFoundException("Não foi encontrada uma conta");
			Empresa empresa = empresaService.getOne(idEmpresa).get();
			if(conta.equals(null)) throw new HomeNotFoundException("Não foi encontrada uma empresa");
			
			if(empresa.equals(null) || conta.equals(null)) {
				return;
			}
			
			monitoramento.setConta(conta);
			monitoramento.setEmpresa(empresa);
			
			
			Float valor = 0F;
			Integer qtde = 0;
			TransacaoAcoes transacao;
			
			monitoramentoService.insert(monitoramento);

			for(int i = 0; i<3; i++) {
				
				if(conta.getSaldo() == 0 || conta.getSaldo() == null) {
					continue;
				}

				transacao = new TransacaoAcoes();
				valor = gerarValor(conta.getValorCompra(), conta.getValorVenda());

				if(!verificarMargem(conta, valor)) {
					continue;
				}				

				qtde = verificarQtde(conta, valor);

				if(valor <= conta.getValorCompra()) {
					
					transacao.setDataTransacao(new Date());
					transacao.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.COMPRA);
					transacao.setQuantidade(qtde);
					transacao.setValor(valor);
					transacao.setMonitoramento(monitoramento);
					transacaoAcoesService.insert(transacao);

					conta.setSaldo(gerarSaldo(qtde, valor, conta.getSaldo(), true));

					if(conta.getQtdeAcoes() == null) {
						conta.setQtdeAcoes(qtde);	
					}else {
						conta.setQtdeAcoes(conta.getQtdeAcoes() + qtde);
					}					
					
					contaService.insert(conta);


				}else if(valor >= conta.getValorVenda()) {

					if(conta.getQtdeAcoes() < 1 || conta.getQtdeAcoes().equals(null)) {
						continue;
					}

					transacao.setDataTransacao(new Date());
					transacao.setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum.VENDA);
					transacao.setQuantidade(conta.getQtdeAcoes());
					transacao.setValor(valor);
					transacao.setMonitoramento(monitoramento);
					transacaoAcoesService.insert(transacao);
					conta.setSaldo(gerarSaldo(conta.getQtdeAcoes(), valor, conta.getSaldo(), false));	
					contaService.insert(conta);
					
				}

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}).start();
	}

	private Boolean verificarMargem(Conta conta, Float valor) {

		if((conta.getSaldo() * conta.getMargemTranscoes()) < valor) {
			return false;
		}

		return true;
	}

	private Double gerarSaldo(Integer qtde, Float valor, Double saldo, boolean b) {

		if(b) {
			return saldo - (qtde * valor);
		}else {
			return saldo + (qtde * valor);
		}

	}

	private Integer verificarQtde(Conta conta, Float valor) {
		Integer qtde = (int) ((conta.getSaldo() * conta.getMargemTranscoes())/valor);
		return qtde;
	}

	private Float gerarValor(Float valorCompra, Float valorVenda) {
		Random random = new Random();

		Float rangeValueMin = valorCompra * 0.9F;
		Float rangeValueMax = valorVenda * 1.1F;
		Float rangeValue = rangeValueMax - rangeValueMin;
		Float valorTransacao = rangeValueMin + rangeValue * random.nextFloat(); 
		return valorTransacao;
	}

}
