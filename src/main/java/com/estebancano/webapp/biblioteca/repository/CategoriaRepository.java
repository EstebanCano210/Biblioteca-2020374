package com.estebancano.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estebancano.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Long>{
    
}
