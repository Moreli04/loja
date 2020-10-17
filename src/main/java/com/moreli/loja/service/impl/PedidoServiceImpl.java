package com.moreli.loja.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moreli.loja.exceptions.ObjectNotFoundException;
import com.moreli.loja.model.Pedido;
import com.moreli.loja.repository.PedidoRepository;
import com.moreli.loja.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Override
	public Pedido findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " tipo: " + Pedido.class.getSimpleName()));
	}
}
