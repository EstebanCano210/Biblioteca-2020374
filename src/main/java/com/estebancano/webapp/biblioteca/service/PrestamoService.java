package com.estebancano.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estebancano.webapp.biblioteca.model.Prestamo;
import com.estebancano.webapp.biblioteca.repository.PrestamoRepository;

@Service
public class PrestamoService implements IPrestamoService{
    @Autowired
    PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listaPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo buscarPrestamoPorId(Long id) {
       return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarPrestamo(Prestamo prestamo) {
       prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);
    }
}
