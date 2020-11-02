package com.moreli.loja.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moreli.loja.enums.EstadoPagamento;
import com.moreli.loja.enums.TipoCliente;
import com.moreli.loja.model.Categoria;
import com.moreli.loja.model.Cidade;
import com.moreli.loja.model.Cliente;
import com.moreli.loja.model.Endereco;
import com.moreli.loja.model.Estado;
import com.moreli.loja.model.ItemPedido;
import com.moreli.loja.model.PagamentoComBoleto;
import com.moreli.loja.model.PagamentoComCartao;
import com.moreli.loja.model.Pedido;
import com.moreli.loja.model.Produto;
import com.moreli.loja.repository.CategoriaRepository;
import com.moreli.loja.repository.CidadeRepository;
import com.moreli.loja.repository.ClienteRepository;
import com.moreli.loja.repository.EstadoRepository;
import com.moreli.loja.repository.PedidoRepository;
import com.moreli.loja.repository.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public void instantiateTestDatabase() {
		Categoria cat1 = new Categoria();
		cat1.setNome("Informática");

		Categoria cat2 = new Categoria();
		cat2.setNome("Escritório");
		
		Categoria cat3 = new Categoria();
		cat3.setNome("Cama mesa e banho");
		
		Categoria cat4 = new Categoria();
		cat4.setNome("Eletrônicos");
		
		Categoria cat5 = new Categoria();
		cat5.setNome("Jardinagem");
		
		Categoria cat6 = new Categoria();
		cat6.setNome("Decoração");
		
		Categoria cat7 = new Categoria();
		cat7.setNome("Perfumaria");

		Produto p1 = new Produto();
		p1.setNome("Computador");
		p1.setPreco(new BigDecimal("2000"));

		Produto p2 = new Produto();
		p2.setNome("Impressora");
		p2.setPreco(new BigDecimal("800"));

		Produto p3 = new Produto();
		p3.setNome("Mouse");
		p3.setPreco(new BigDecimal("80"));
		
		Produto p4 = new Produto();
		p4.setNome("Mesa de escritório");
		p4.setPreco(new BigDecimal("80"));
		
		Produto p5 = new Produto();
		p5.setNome("Toalha");
		p5.setPreco(new BigDecimal("50.00"));
		
		Produto p6 = new Produto();
		p6.setNome("Colcha");
		p6.setPreco(new BigDecimal("200.00"));
		
		Produto p7 = new Produto();
		p7.setNome("TV true color");
		p7.setPreco(new BigDecimal("1200.00"));
		
		Produto p8 = new Produto();
		p8.setNome("Roçadeira");
		p8.setPreco(new BigDecimal("800.00"));
		
		Produto p9 = new Produto();
		p9.setNome("Abajour");
		p9.setPreco(new BigDecimal("100.00"));
		
		Produto p10 = new Produto();
		p10.setNome("Pendente");
		p10.setPreco(new BigDecimal("180.00"));
		
		Produto p11 = new Produto();
		p11.setNome("Shampoo");
		p11.setPreco(new BigDecimal("90.00"));

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));		

		final Estado est1 = new Estado();
		est1.setNome("Minas Gerais");

		final Estado est2 = new Estado();
		est2.setNome("São Paulo");

		Cidade cid1 = new Cidade();
		cid1.setNome("Uberlândia");
		cid1.setEstado(est1);

		Cidade cid2 = new Cidade();
		cid2.setNome("São Paulo");
		cid2.setEstado(est2);

		Cidade cid3 = new Cidade();
		cid3.setNome("Campinas");
		cid3.setEstado(est2);

		est1.getCidades().addAll(Collections.singletonList(cid1));
		est2.setCidades(Arrays.asList(cid2, cid3));
		
		Cliente cli1 = new Cliente();
		cli1.setNome("Maria Silva");
		cli1.setEmail("maria@gmail.com");
		cli1.setCpfOuCnpj("36378912377");
		cli1.setTipo(TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco();
		e1.setLogradouro("Rua Flores");
		e1.setNumero("300");
		e1.setComplemento("Apto 303");
		e1.setBairro("Jardim");
		e1.setCep("38220834");
		e1.setCliente(cli1);
		e1.setCidade(cid1);

		Endereco e2 = new Endereco();
		e2.setLogradouro("Avenida Matos");
		e2.setNumero("105");
		e2.setComplemento("Sala 800");
		e2.setBairro("Centro");
		e2.setCep("38777012");
		e2.setCliente(cli1);
		e2.setCidade(cid1);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		final Pedido ped1 = new Pedido();
		ped1.setInstante(LocalDateTime.of(2017, 9, 30, 10, 32));
		ped1.setCliente(cli1);
		ped1.setEnderecoDeEntrega(e1);

		final Pedido ped2 = new Pedido();
		ped2.setInstante(LocalDateTime.of(2017, 10, 10, 19, 35));
		ped2.setCliente(cli1);
		ped2.setEnderecoDeEntrega(e2);

		final PagamentoComCartao pagto1 = new PagamentoComCartao();
		pagto1.setEstado(EstadoPagamento.QUITADO);
		pagto1.setPedido(ped1);
		pagto1.setNumeroDeParcelas(6L);
		ped1.setPagamento(pagto1);

		final PagamentoComBoleto pagto2 = new PagamentoComBoleto();
		pagto2.setEstado(EstadoPagamento.PENDENTE);
		pagto2.setPedido(ped2);
		pagto2.setDataVencimento(LocalDate.of(2017, 10, 20));
		ped2.setPagamento(pagto2);
		
		final ItemPedido ip1 = new ItemPedido();
		ip1.setPedido(ped1);
		ip1.setProduto(p1);
		ip1.setDesconto(BigDecimal.ZERO);
		ip1.setQuantidade(1L);
		ip1.setPreco(new BigDecimal("2000.00"));
		
		final ItemPedido ip2 = new ItemPedido();
		ip2.setPedido(ped1);
		ip2.setProduto(p3);
		ip2.setDesconto(BigDecimal.ZERO);
		ip2.setQuantidade(2L);
		ip2.setPreco(new BigDecimal("80.00"));
		
		final ItemPedido ip3 = new ItemPedido();
		ip3.setPedido(ped2);
		ip3.setProduto(p2);
		ip3.setDesconto(new BigDecimal("100.00"));
		ip3.setQuantidade(1L);
		ip3.setPreco(new BigDecimal("800.00"));
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Collections.singletonList(ip3));
		
		p1.getItens().addAll(Collections.singletonList(ip1));
		p2.getItens().addAll(Collections.singletonList(ip3));
		p3.getItens().addAll(Collections.singletonList(ip2));

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
	}
}
