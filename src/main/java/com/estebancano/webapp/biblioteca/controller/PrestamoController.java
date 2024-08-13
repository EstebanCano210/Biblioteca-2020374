package com.estebancano.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estebancano.webapp.biblioteca.model.Prestamo;
import com.estebancano.webapp.biblioteca.service.PrestamoService;

@Controller
@RestController
@RequestMapping("prestamo")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @GetMapping("/")
    public ResponseEntity<List<Prestamo>> listarPrestamos(){
        try {
            return ResponseEntity.ok(prestamoService.listaPrestamos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> buscarPrestamo(@PathVariable Long id){
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            return ResponseEntity.ok(prestamo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Agregar
    @PostMapping("/")
    public ResponseEntity<Map<String, Boolean>> agregarPrestamo(@RequestBody Prestamo prestamo){
        Map<String, Boolean> response = new HashMap<>();
        try {
            prestamoService.guardarPrestamo(prestamo);
            response.put("El prestamo Se ha realizado con Exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Prestamo No realizado", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar 
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> editarLibro(@PathVariable Long id, Prestamo prestamoNuevo){
        Map<String, Boolean> response = new HashMap<>();
        try {
            Prestamo prestamoViejo = prestamoService.buscarPrestamoPorId(id);
            prestamoViejo.setFechaDePrestamo(prestamoNuevo.getFechaDePrestamo());
            prestamoViejo.setFechaDeDevolucion(prestamoNuevo.getFechaDeDevolucion());
            prestamoViejo.setVigencia(prestamoNuevo.getVigencia());
            response.put("EL prestamo se ha editado con exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("No se ha realizado la edicion", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarPrestamo(@PathVariable Long id){
        Map<String, Boolean> response = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamoService.eliminarPrestamo(prestamo);
            response.put("Se ha eliminado el Prestamo", Boolean.TRUE);        
            return ResponseEntity.ok(response);   
        } catch (Exception e) {
            response.put("No se ha podido eliminar el Prestamo", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);    
        }
    }
}
