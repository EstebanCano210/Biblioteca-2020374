package com.estebancano.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estebancano.webapp.biblioteca.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro , Long>{

}
