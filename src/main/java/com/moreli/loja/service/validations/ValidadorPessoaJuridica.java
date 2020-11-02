package com.moreli.loja.service.validations;

import java.util.List;

import com.moreli.loja.enums.TipoCliente;
import com.moreli.loja.exceptions.FieldMessage;
import com.moreli.util.BR;

public class ValidadorPessoaJuridica extends ValidadorTipoCliente{
	
	@Override
	public boolean aceitar(TipoCliente tipo, String cpfOuCnpj) {
		return TipoCliente.PESSOA_JURIDICA.equals(tipo);
	}

	@Override
	public List<FieldMessage> tratar(String cpfOuCnpj) {
		fieldsMessage.clear();
		if(!BR.isValidCNPJ(cpfOuCnpj)) {
			fieldsMessage.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		return fieldsMessage;
	}

}
