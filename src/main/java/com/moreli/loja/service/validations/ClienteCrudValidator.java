package com.moreli.loja.service.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerMapping;

import com.moreli.loja.dto.ClienteDTO;
import com.moreli.loja.enums.TipoCliente;
import com.moreli.loja.exceptions.FieldMessage;
import com.moreli.loja.model.Cliente;
import com.moreli.loja.repository.ClienteRepository;
import com.moreli.loja.utils.BR;

public class ClienteCrudValidator implements ConstraintValidator<ClienteCrud, ClienteDTO> {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void initialize(ClienteCrud ann) {
	}

	@Override
	public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {

		List<FieldMessage> list;
		if (HttpMethod.POST.matches(request.getMethod())) {
			list = validateInsert(dto, context);
		} else {
			list = validateUpdate(dto, context);
		}

		list.forEach((FieldMessage e) -> {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		});

		return list.isEmpty();
	}

	public List<FieldMessage> validateInsert(ClienteDTO dto, ConstraintValidatorContext context) {
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

		return list;
	}

	public List<FieldMessage> validateUpdate(ClienteDTO dto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		final Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		final Long uriId = Long.parseLong(map.get("id"));

		final List<FieldMessage> list = new ArrayList<>();

		Cliente aux = repository.findByEmail(dto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		return list;
	}
}
