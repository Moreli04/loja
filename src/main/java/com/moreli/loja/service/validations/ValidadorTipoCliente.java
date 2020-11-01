package com.moreli.loja.service.validations;

import java.util.ArrayList;
import java.util.List;

import com.moreli.loja.enums.TipoCliente;
import com.moreli.loja.exceptions.FieldMessage;

public abstract class ValidadorTipoCliente {

	protected final List<FieldMessage> fieldsMessage = new ArrayList<>();
	
	abstract boolean aceitar(TipoCliente tipo, String cpfOuCnpj) ;
	abstract List<FieldMessage> tratar(String cpfOuCnpj);
}
