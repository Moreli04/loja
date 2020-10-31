package com.moreli.loja.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.moreli.loja.enums.TipoCliente;
import com.moreli.loja.service.validations.ClienteInsert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ClienteInsert
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;
	
	private Integer tipo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private	List<EnderecoDTO> enderecos = new ArrayList<>();
	
	@NotEmpty(message="Preenchimento obrigatório")
	private List<String> telefones = new ArrayList<>();
	
	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}
	
	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}
}
