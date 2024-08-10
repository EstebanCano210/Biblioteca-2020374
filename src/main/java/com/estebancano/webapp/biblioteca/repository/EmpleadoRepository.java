package com.estebancano.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estebancano.webapp.biblioteca.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

}
