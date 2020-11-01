package com.moreli.loja.exceptions;

public class InvalidClientType extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private static String MESSAGE = "Tipo de cliente inválido";
	
	public InvalidClientType() {
		super(MESSAGE);
	}
	
	public InvalidClientType(Throwable cause) {
		super(MESSAGE, cause);
	}

}
