package com.estebancano.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estebancano.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro , Long>{

}
