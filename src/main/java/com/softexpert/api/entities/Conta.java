package com.softexpert.api.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name="tb_conta")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "conta")
	@JoinColumn(name = "pessoa")
	@JsonIgnore
	private Pessoa pessoa;
	
	@OneToMany(mappedBy = "conta")
	private List<Monitoramento> monitoramento;
	
	@OneToMany(mappedBy = "conta")
	private List<TransacaoConta> transacoesConta;
	
	private Double saldo;
	
	@NotNull
	@Column(name = "margem")
	private Float margemTranscoes;	
	
	@NotNull
	@Column(name = "valor_compra")
	private Float valorCompra;
	
	@NotNull
	@Column(name = "valor_venda")
	private Float valorVenda;
	
	@Column(name = "qtde_acoes")
	private Integer qtdeAcoes;

	public Conta() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<TransacaoConta> getTransacoesConta() {
		return transacoesConta;
	}

	public void setTransacoesConta(List<TransacaoConta> transacoesConta) {
		this.transacoesConta = transacoesConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}	

	public Float getMargemTranscoes() {
		return margemTranscoes;
	}

	public void setMargemTranscoes(Float margemTranscoes) {
		this.margemTranscoes = margemTranscoes;
	}

	public Float getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Float valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Float getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Float valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Integer getQtdeAcoes() {
		return qtdeAcoes;
	}

	public void setQtdeAcoes(Integer qtdeAcoes) {
		this.qtdeAcoes = qtdeAcoes;
	}
	
	public List<Monitoramento> getMonitoramento() {
		return monitoramento;
	}

	public void setMonitoramento(List<Monitoramento> monitoramento) {
		this.monitoramento = monitoramento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
