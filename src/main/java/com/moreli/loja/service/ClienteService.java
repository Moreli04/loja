package com.moreli.loja.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.moreli.loja.model.Cliente;

public interface ClienteService {

	Cliente findById(Long id);

	Cliente insert(Cliente cliente);

	Cliente update(Cliente cliente);

	List<Cliente> findAll();

	Page<Cliente> findAll(Pageable pageable);

	void delete(Long id);
}
