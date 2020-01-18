package com.softexpert.api.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.softexpert.api.entities.enums.TipoTransacaoAcoesEnum;


@Entity
@Table(name="tb_transacao")
public class TransacaoAcoes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="conta", referencedColumnName = "id")
	private Conta conta;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_transacao_acoes")
	private TipoTransacaoAcoesEnum tipoTransacaoAcoesEnum;
	
	@Column(name="data_transacao")
	private Date dataTransacao;
	
	private Float valor;
	
	private Integer quantidade;

	public TransacaoAcoes() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public TipoTransacaoAcoesEnum getTipoTransacaoAcoesEnum() {
		return tipoTransacaoAcoesEnum;
	}

	public void setTipoTransacaoAcoesEnum(TipoTransacaoAcoesEnum tipoTransacaoAcoesEnum) {
		this.tipoTransacaoAcoesEnum = tipoTransacaoAcoesEnum;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
		TransacaoAcoes other = (TransacaoAcoes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
