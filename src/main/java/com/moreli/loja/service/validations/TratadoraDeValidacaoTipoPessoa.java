package com.moreli.loja.service.validations;

import java.util.Arrays;
import java.util.List;

import com.moreli.loja.enums.TipoCliente;
import com.moreli.loja.exceptions.FieldMessage;
import com.moreli.loja.exceptions.InvalidClientType;

public class TratadoraDeValidacaoTipoPessoa {
	private final List<ValidadorTipoCliente> validadores;
	
	public TratadoraDeValidacaoTipoPessoa() {
		this.validadores = Arrays.asList(new ValidadorPessoaFisica(), new ValidadorPessoaJuridica());
	}
	
	public List<FieldMessage> processar(TipoCliente tipo, String cpfOuCnpj){
		for (ValidadorTipoCliente validador : validadores) {
			if(validador.aceitar(tipo, cpfOuCnpj)) {
				return validador.tratar(cpfOuCnpj);
			}
		}
		throw new InvalidClientType();
	}
}
