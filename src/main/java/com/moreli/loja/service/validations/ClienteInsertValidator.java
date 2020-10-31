package com.moreli.loja.service.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.moreli.loja.dto.ClienteDTO;
import com.moreli.loja.enums.TipoCliente;
import com.moreli.loja.exceptions.FieldMessage;
import com.moreli.loja.model.Cliente;
import com.moreli.loja.repository.ClienteRepository;
import com.moreli.loja.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDTO> {

	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (dto.getTipo().equals(TipoCliente.PESSOA_FISICA) && !BR.isValidCPF(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (dto.getTipo().equals(TipoCliente.PESSOA_JURIDICA) && !BR.isValidCNPJ(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		Cliente aux = repository.findByEmail(dto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		list.forEach((FieldMessage e) ->{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		});

		return list.isEmpty();
	}
}
