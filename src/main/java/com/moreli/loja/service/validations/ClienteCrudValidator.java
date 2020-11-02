package com.moreli.loja.service.validations;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerMapping;

import com.moreli.loja.dto.ClienteDTO;
import com.moreli.loja.exceptions.FieldMessage;
import com.moreli.loja.model.Cliente;
import com.moreli.loja.repository.ClienteRepository;

public class ClienteCrudValidator implements ConstraintValidator<ClienteCrud, ClienteDTO> {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private HttpServletRequest request;
	
	private final TratadoraDeValidacaoTipoPessoa tratadoraDeValidacaoTipoPessoa = new TratadoraDeValidacaoTipoPessoa();

	@Override
	public void initialize(ClienteCrud ann) {
	}

	@Override
	public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list;
		
		if (HttpMethod.POST.matches(request.getMethod())) {
			list = validateInsert(dto);
		} else {
			list = validateUpdate(dto);
		}

		copiarErrosParaConstraintValidatorContext(context, list);
		return list.isEmpty();
	}

	public List<FieldMessage> validateInsert(ClienteDTO dto) {
		final List<FieldMessage> fieldsMessage = 
				tratadoraDeValidacaoTipoPessoa.processar(dto.getTipo(), dto.getCpfOuCnpj());

		adicionarErroCasoEmailEstejaEmUsoPorOutroCliente(dto, fieldsMessage);
		return fieldsMessage;
	}

	public List<FieldMessage> validateUpdate(ClienteDTO dto) {
		final Long uriId = extrairIdDaURI();

		final List<FieldMessage> fieldsMessage = 
				tratadoraDeValidacaoTipoPessoa.processar(dto.getTipo(), dto.getCpfOuCnpj());

		adicionarErroCasoEmailEstejaEmUsoPorOutroCliente(dto, fieldsMessage, uriId);
		return fieldsMessage;
	}
	
	private void adicionarErroCasoEmailEstejaEmUsoPorOutroCliente(ClienteDTO dto,
			final List<FieldMessage> fieldsMessage) {
		
		repository.findByEmail(dto.getEmail())
			.ifPresent((Cliente clienteDB) ->
				fieldsMessage.add(new FieldMessage("email", "Email já existente")));
	}

	private void adicionarErroCasoEmailEstejaEmUsoPorOutroCliente(ClienteDTO dto,
			final List<FieldMessage> fieldsMessage, final Long uriId) {
		
		repository.findByEmail(dto.getEmail()).ifPresent((Cliente clienteDB) ->{
			if (!clienteDB.getId().equals(uriId)) {
				fieldsMessage.add(new FieldMessage("email", "Email já existente"));
			}	
		});
	}

	private Long extrairIdDaURI() {
		@SuppressWarnings("unchecked")
		final Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		return	Long.parseLong(map.get("id"));
	}
	
	private void copiarErrosParaConstraintValidatorContext(ConstraintValidatorContext context,
			List<FieldMessage> list) {
		
		list.forEach((FieldMessage e) -> {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		});
	}
}
