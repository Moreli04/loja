package com.moreli.loja.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.moreli.loja.model.Categoria;

public interface CategoriaService {

	Categoria findById(Long id);
	List<Categoria> findAll();
	Categoria insert(Categoria categoria);
	Categoria update(Categoria categoria);
	void delete(Long id);
	Page<Categoria> findAll(Pageable pageable);
}
