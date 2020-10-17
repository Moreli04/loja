package com.moreli.loja.enums;

import lombok.Getter;

@Getter
public enum EstadoPagamento{

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static EstadoPagamento toEnum(int cod) {
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod == x.getCod()) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
