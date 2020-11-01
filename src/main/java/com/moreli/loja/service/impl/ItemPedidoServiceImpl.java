package com.moreli.loja.service.impl;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moreli.loja.model.ItemPedido;
import com.moreli.loja.service.ProdutoService;

@Service
public class ItemPedidoServiceImpl {
	
	@Autowired
	private ProdutoService produtoService;
	
	public void prepararParaInsert(Set<ItemPedido> itensPedido) {
		itensPedido.forEach(itemPedido ->{
			itemPedido.setDesconto(BigDecimal.ZERO);
			itemPedido.setProduto(produtoService.findById(itemPedido.getProduto().getId()));
			itemPedido.setPreco(itemPedido.getProduto().getPreco());
		});
	}

}
