package com.moreli.loja.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moreli.loja.enums.EstadoPagamento;
import com.moreli.loja.exceptions.ObjectNotFoundException;
import com.moreli.loja.model.PagamentoComBoleto;
import com.moreli.loja.model.Pedido;
import com.moreli.loja.repository.PedidoRepository;
import com.moreli.loja.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired 
	private ItemPedidoServiceImpl itemPedidoService;
	
	@Autowired
	private PagamentoComBoletoServiceImpl pagamentoComBoletoService;

	@Override
	@Transactional(readOnly = true)
	public Pedido findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " titemPedidoo: " + Pedido.class.getSimpleName()));
	}

	@Override
	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setInstante(LocalDateTime.now());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			final PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
			pagamentoComBoletoService.prepararParaInsert(pagamento);
		}
		
		itemPedidoService.prepararParaInsert(pedido.getItens());
		repository.save(pedido);
		return pedido;
	}

}
