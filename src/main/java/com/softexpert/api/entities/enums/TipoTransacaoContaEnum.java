package com.softexpert.api.entities.enums;

public enum TipoTransacaoContaEnum {
	SAQUE("Saque"),
	DEPOSITO("Depósito");
	
	private final String descricao;
	
	private TipoTransacaoContaEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}
}
