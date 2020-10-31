package com.moreli.loja.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String logradouro;

	@NotEmpty(message="Preenchimento obrigatório")
	private String numero;

	private String complemento;

	private String bairro;

	@NotEmpty(message="Preenchimento obrigatório")
	private String cep;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Long cidadeId;	
	
	
}
