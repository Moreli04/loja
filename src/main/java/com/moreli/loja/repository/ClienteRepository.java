package com.moreli.loja.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moreli.loja.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Transactional(readOnly = true)
	Optional<Cliente> findByEmail(String email);

}
