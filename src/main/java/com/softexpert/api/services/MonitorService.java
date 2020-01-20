package com.softexpert.api.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softexpert.api.entities.Conta;
import com.softexpert.api.entities.Empresa;
import com.softexpert.api.entities.Monitoramento;
import com.softexpert.api.entities.TransacaoAcoes;
import com.softexpert.api.entities.TransacaoConta;
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
	
	@Autowired
	TransacaoContaService transacaoContaService;

	Date start, end;
	
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
			this.start = new Date();
			
			for(int i = 0; i<20; i++) {
				
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
					conta.setQtdeAcoes(0);
					contaService.insert(conta);
					
				}

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.end = new Date();
			gerarRelatorio(monitoramento, conta);

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
	
	private void gerarRelatorio(Monitoramento monitoramento, Conta conta) {
		
		String fileName = "relatorio_geral_de_monitoramento_"+ monitoramento.getId() +".txt";
		
		try {
			FileOutputStream fileOutputStream;
			PrintStream printStream;
			fileOutputStream = new FileOutputStream(new File(fileName).getAbsoluteFile(), true);
			printStream = new PrintStream(fileOutputStream);

			List<TransacaoAcoes> compras = transacaoAcoesService.getTransacoesPorTipoAndMonitoramento(TipoTransacaoAcoesEnum.COMPRA, monitoramento);
			List<TransacaoAcoes> vendas = transacaoAcoesService.getTransacoesPorTipoAndMonitoramento(TipoTransacaoAcoesEnum.VENDA, monitoramento);
			List<TransacaoConta> transacoesContas = transacaoContaService.getAllBetween(this.start, this.end);
			
			
			printStream.println("----------------------------------------------------------------------");
			printStream.println("Monitoramento feito sobre a conta: " + conta.getPessoa().getEmail());
			printStream.println("Empresa monitorada: " + monitoramento.getEmpresa().getNome());
			printStream.println("----------------------------------------------------------------------");

			printStream.println();
			
			printStream.println("Margem de transação permitida: " + conta.getMargemTranscoes());
			printStream.println("Saldo final: " + conta.getSaldo());

			printStream.println();
			
			printStream.println("----------------------------------------------------------------------");
			printStream.println("Compras feitas no período de 100 iterações");
			
			printStream.println();
			
			for(TransacaoAcoes compra: compras) {
				printStream.println("----------------------------------------------------------------------");
				printStream.println("Id: " + compra.getId());
				printStream.println("Data e hora:" + compra.getDataTransacao());
				printStream.println("Valor: " + compra.getValor());
				printStream.println("Quantidade: " + compra.getQuantidade());
				printStream.println("----------------------------------------------------------------------");
			}
			printStream.println();
			printStream.println("Vendas feitas no período de 100 iterações");
			printStream.println();
			
			
			for(TransacaoAcoes venda: vendas) {
				printStream.println("----------------------------------------------------------------------");
				printStream.println("Id: " + venda.getId());
				printStream.println("Data e hora:" + venda.getDataTransacao());
				printStream.println("Valor: " + venda.getValor());
				printStream.println("Quantidade: " + venda.getQuantidade());
				printStream.println("----------------------------------------------------------------------");
			}
			
			printStream.println();
			printStream.println();
			printStream.println("----------------------------------------------------------------------");
			printStream.println("Depósitos e saques emitidos no intervalo das iterações");
			for(TransacaoConta trans : transacoesContas) {
				printStream.println("----------------------------------------------------------------------");
				printStream.println("Data" + trans.getDataTransacao());	
				printStream.println("Tipo da transação: " + trans.getTipoTransacaoContaEnum().getDescricao());
				printStream.println("Valor da transação: " + trans.getValor());
				printStream.println("----------------------------------------------------------------------");
			}
			
			printStream.close();
			
			sendMail(conta, fileName);	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMail(Conta conta, String fileName) {

		/* String to = conta.getPessoa().getEmail(); */
		String to = "nazario@alunos.utfpr.edu.br";
		String from = "nazario@alunos.utfpr.edu.br";
		String host = "smtp.gmail.com";
		String filename = fileName;
		String msgText1 = "Segue em anexo, relatório de transações.";
		String subject = "Relatório";

		// cria algumas propriedades e obtem uma sessao padrao
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", 
	    "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");
	    
		Session session = Session.getInstance(props,
			      new javax.mail.Authenticator() {
			           protected PasswordAuthentication getPasswordAuthentication() 
			           {
			                 return new PasswordAuthentication("nazario@alunos.utfpr.edu.br", 
			                 "tgsnzs70");
			           }
			      });

		try 
		{
			// cria a mensagem
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = {new InternetAddress(to)};
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);


			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msgText1);


			MimeBodyPart mbp2 = new MimeBodyPart();


			FileDataSource fds = new FileDataSource(filename);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());


			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);


			msg.setContent(mp);

			msg.setSentDate(new Date());

			Transport.send(msg);

		} 
		catch (MessagingException mex){
			mex.printStackTrace();
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				ex.printStackTrace();
			}			
		}
		System.out.println("email enviado com sucesso");
	}

}
