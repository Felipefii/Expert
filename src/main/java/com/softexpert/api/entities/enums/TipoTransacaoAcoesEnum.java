package com.softexpert.api.entities.enums;

public enum TipoTransacaoAcoesEnum {

	COMPRA("Compra de ações"),
	VENDA("Venda de ações");
	
	private final String descricao;
	
	private TipoTransacaoAcoesEnum(String descricao) {
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
