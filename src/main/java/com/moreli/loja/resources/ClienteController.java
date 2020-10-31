package com.moreli.loja.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.moreli.loja.dto.ClienteDTO;
import com.moreli.loja.model.Cliente;
import com.moreli.loja.service.ClienteService;
import com.moreli.util.ModelUtils;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll()  {
		return ResponseEntity.ok().body(ModelUtils.convertModelList(service.findAll(), ClienteDTO.class));
	}
	
	@GetMapping("/paginada")
	public Page<Cliente> findAll(@PageableDefault Pageable pageable)  {
		return service.findAll(pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO clienteDTO){
		Cliente cliente = ModelUtils.convertModel(clienteDTO, Cliente.class);
		cliente = service.insert(cliente);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO){
		Cliente cliente = ModelUtils.convertModel(clienteDTO, Cliente.class);
		cliente.setId(id);
		cliente = service.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
