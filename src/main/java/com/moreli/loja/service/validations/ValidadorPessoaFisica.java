package com.moreli.loja.service.validations;

import java.util.List;

import com.moreli.loja.enums.TipoCliente;
import com.moreli.loja.exceptions.FieldMessage;
import com.moreli.util.BR;

public class ValidadorPessoaFisica extends ValidadorTipoCliente{
	
	@Override
	public boolean aceitar(TipoCliente tipo, String cpfOuCnpj) {
		return TipoCliente.PESSOA_FISICA.equals(tipo);
	}

	@Override
	public List<FieldMessage> tratar(String cpfOuCnpj) {
		fieldsMessage.clear();
		if(!BR.isValidCPF(cpfOuCnpj)){
			fieldsMessage.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		return fieldsMessage;
	}

}
