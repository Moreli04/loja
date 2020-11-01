package com.moreli.loja.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moreli.loja.exceptions.DataIntegrityException;
import com.moreli.loja.exceptions.ObjectNotFoundException;
import com.moreli.loja.model.Cliente;
import com.moreli.loja.repository.ClienteRepository;
import com.moreli.loja.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " tipo: " + Cliente.class.getSimpleName()));
	}
	
	@Override
	@Transactional
	public Cliente insert(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	@Transactional
	public Cliente update(Cliente cliente) {
		final Cliente novoCliente = findById(cliente.getId());
		updateData(cliente, novoCliente);
		return repository.save(novoCliente);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		validarExclusao(id);
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	private void updateData(Cliente cliente, final Cliente novoCliente) {
		novoCliente.setNome(cliente.getNome());
		novoCliente.setEmail(cliente.getEmail());
	}
	
	private void validarExclusao(Long id) {
		final Cliente cliente = findById(id);
		
		if(CollectionUtils.isNotEmpty(cliente.getPedidos())) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
		}
	}
}
