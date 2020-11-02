package com.moreli.loja.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moreli.loja.dto.ProdutoDTO;
import com.moreli.loja.model.Produto;
import com.moreli.loja.service.ProdutoService;
import com.moreli.util.ModelUtils;
import com.moreli.util.Url;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public Page<ProdutoDTO> findAll(@PageableDefault Pageable pageable,
			@RequestParam(defaultValue = "") String nome, 
			@RequestParam(defaultValue = "") List<Long> ids)  {
		final String nomeEmUtf8 = Url.decodeParam(nome);
		final Page<Produto> pageProdutos = service.findByFilter(nomeEmUtf8, ids, pageable);
		final Page<ProdutoDTO> pageProdutosDTO = pageProdutos.map(produto -> ModelUtils.convertModel(produto, ProdutoDTO.class));  
		return pageProdutosDTO;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(ModelUtils.convertModel(service.findById(id), ProdutoDTO.class));
	}
	
}
