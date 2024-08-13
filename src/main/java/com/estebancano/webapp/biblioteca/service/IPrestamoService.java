package com.estebancano.webapp.biblioteca.service;

import java.util.List;

import com.estebancano.webapp.biblioteca.model.Prestamo;

public interface IPrestamoService {

    public List<Prestamo> listaPrestamos();

    public Prestamo buscarPrestamoPorId(Long id);

    public void guardarPrestamo(Prestamo prestamo);

    public void eliminarPrestamo(Prestamo prestamo);
}
