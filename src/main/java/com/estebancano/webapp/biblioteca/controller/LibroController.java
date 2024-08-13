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

import com.estebancano.webapp.biblioteca.model.Libro;
import com.estebancano.webapp.biblioteca.service.LibroService;

@Controller
@RestController
@RequestMapping("libro")
public class LibroController {

    @Autowired
    LibroService libroService;

    //Listar
    @GetMapping("/")
    public ResponseEntity<List<Libro>> listarLibros(){
        try {
            return ResponseEntity.ok(libroService.listarLibros());   
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Buscar
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarLibro(@PathVariable Long id){
        try {
            Libro libro = libroService.buscarLibroPorId(id);
            return ResponseEntity.ok(libro);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Agregar
    @PostMapping("/")
    public ResponseEntity<Map<String, Boolean>> agregarLibro(@RequestBody Libro libro){
        Map<String, Boolean> response = new HashMap<>();
        try {
            libroService.guardarLibro(libro);
            response.put("El Libro se ha Agregado con Exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("El libro no se ha agregado", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> editarLibro(@PathVariable Long id,  @RequestBody Libro libroNuevo){
        Map<String, Boolean> response = new HashMap<>();
        try {
            Libro libroViejo = libroService.buscarLibroPorId(id);
            libroViejo.setIsbn(libroNuevo.getIsbn());
            libroViejo.setNombre(libroNuevo.getNombre());
            libroViejo.setSinopsis(libroNuevo.getSinopsis());
            libroViejo.setAutor(libroNuevo.getAutor());
            libroViejo.setEditorial(libroNuevo.getEditorial());
            libroViejo.setDisponibilidad(libroNuevo.getDisponibilidad());
            libroViejo.setNumeroEstanteria(libroNuevo.getNumeroEstanteria());
            libroViejo.setCluster(libroNuevo.getCluster());
            libroViejo.setCategoria(libroNuevo.getCategoria());
            libroService.guardarLibro(libroViejo);
            response.put("La edición del Libro ha sido Exitosa", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("No se ha realizado la edicion", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarLibro(@PathVariable Long id){
        Map<String, Boolean> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibroPorId(id);
            libroService.eliminarLibro(libro);
            response.put("El libro ha sido Eliminado con Exito", Boolean.TRUE);        
            return ResponseEntity.ok(response);   
        } catch (Exception e) {
            response.put("El libro no se ha podido Eliminar", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);    
        }
    }
}