package com.moreli.loja.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	private Map<String, List<String>> errors = new HashMap<>();

	public ValidationError(Integer status, String msg, Long timestemp) {
		super(status, msg, timestemp);
	}
	
	public void addError(String fieldName, String message) {
		List<String> messages = errors.get(fieldName);
		
		if(messages == null) {
			errors.put(fieldName, Collections.singletonList(message));
		}else {
			final List<String> novaListaDeErros = new ArrayList<>();
			novaListaDeErros.addAll(messages);
			novaListaDeErros.add(message);
			errors.put(fieldName, novaListaDeErros);
		}
	}
}
