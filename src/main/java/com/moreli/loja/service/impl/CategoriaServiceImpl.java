package com.moreli.loja.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moreli.loja.exceptions.ObjectNotFoundException;
import com.moreli.loja.model.Categoria;
import com.moreli.loja.repository.CategoriaRepository;
import com.moreli.loja.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Override
	public Categoria findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " tipo: " + Categoria.class.getSimpleName()));
	}
}
