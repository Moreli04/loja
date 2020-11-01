package com.moreli.loja.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.moreli.loja.model.Produto;

public interface ProdutoService {

	Produto findById(Long id);

	Page<Produto> findByFilter(String nome, List<Long> ids, Pageable pageable);

}
