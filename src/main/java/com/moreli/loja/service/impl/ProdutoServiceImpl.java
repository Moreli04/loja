package com.moreli.loja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moreli.loja.exceptions.ObjectNotFoundException;
import com.moreli.loja.model.Categoria;
import com.moreli.loja.model.Produto;
import com.moreli.loja.repository.CategoriaRepository;
import com.moreli.loja.repository.ProdutoRepository;
import com.moreli.loja.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	@Transactional(readOnly = true)
	public Produto findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " tipo: " + Produto.class.getSimpleName()));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Produto> findByFilter(String nome, List<Long> ids, Pageable pageable) {
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageable);	
	}
}
