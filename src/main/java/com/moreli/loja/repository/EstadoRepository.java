package com.moreli.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moreli.loja.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
