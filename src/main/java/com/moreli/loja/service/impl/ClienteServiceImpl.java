package com.moreli.loja.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moreli.loja.exceptions.ObjectNotFoundException;
import com.moreli.loja.model.Cliente;
import com.moreli.loja.repository.ClienteRepository;
import com.moreli.loja.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Override
	public Cliente findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " tipo: " + Cliente.class.getSimpleName()));
	}
}
