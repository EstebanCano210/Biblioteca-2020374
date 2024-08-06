package com.estebancano.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estebancano.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
