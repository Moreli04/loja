package com.moreli.loja.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moreli.loja.model.Pedido;
import com.moreli.loja.service.PedidoService;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable(name = "id") Long id) {
		final Pedido pedido = service.findById(id);
		return ResponseEntity.ok(pedido);
	}
	
}
