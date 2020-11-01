package com.moreli.loja.dto.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFilter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private List<Long> ids = new ArrayList<>();

}
