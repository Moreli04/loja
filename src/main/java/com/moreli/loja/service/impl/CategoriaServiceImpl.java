package com.moreli.loja.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moreli.loja.exceptions.DataIntegrityException;
//import org.apache.commons.collections.CollectionUtils;
import com.moreli.loja.exceptions.ObjectNotFoundException;
import com.moreli.loja.model.Categoria;
import com.moreli.loja.repository.CategoriaRepository;
import com.moreli.loja.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Categoria findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " tipo: " + Categoria.class.getSimpleName()));
	}

	@Override
	@Transactional
	public Categoria insert(Categoria categoria) {
		return repository.save(categoria);
	}

	@Override
	@Transactional
	public Categoria update(Categoria categoria) {
		final Categoria novaCategoria = findById(categoria.getId());
		updateData(categoria, novaCategoria);
		return repository.save(categoria);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		validarExclusao(id);
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Categoria> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	private void validarExclusao(Long id) {
		final Categoria categoria = findById(id);
		
		if(CollectionUtils.isNotEmpty(categoria.getProdutos())) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	private void updateData(Categoria categoria, final Categoria novaCategoria) {
		novaCategoria.setNome(categoria.getNome());
	}
}
