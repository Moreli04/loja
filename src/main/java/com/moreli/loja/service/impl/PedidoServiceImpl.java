package com.moreli.loja.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moreli.loja.enums.EstadoPagamento;
import com.moreli.loja.exceptions.ObjectNotFoundException;
import com.moreli.loja.model.ItemPedido;
import com.moreli.loja.model.PagamentoComBoleto;
import com.moreli.loja.model.Pedido;
import com.moreli.loja.repository.ItemPedidoRepository;
import com.moreli.loja.repository.PagamentoRepository;
import com.moreli.loja.repository.PedidoRepository;
import com.moreli.loja.repository.ProdutoRepository;
import com.moreli.loja.service.PedidoService;
import com.moreli.loja.service.ProdutoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
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
		pedido.getPagamento().setPedido(pedido);
		
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			final PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
			pagamentoComBoletoService.prepararPagamentoParaInclusao(pagamento);
		}
		repository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		
		for (ItemPedido itemPedido : pedido.getItens()) {
			itemPedido.setDesconto(BigDecimal.ZERO);
			itemPedido.setProduto(produtoService.findById(itemPedido.getProduto().getId()));
			itemPedido.setPreco(itemPedido.getProduto().getPreco());
			itemPedido.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		return pedido;
	}
}
