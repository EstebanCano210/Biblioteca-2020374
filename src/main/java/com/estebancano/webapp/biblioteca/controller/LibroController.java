package com.estebancano.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estebancano.webapp.biblioteca.model.Libro;
import com.estebancano.webapp.biblioteca.service.LibroService;

@Controller
@RestController
@RequestMapping("")
public class LibroController {

    @Autowired
    LibroService libroService;

    //Listar
    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> listarLibros(){
        try {
            return ResponseEntity.ok(libroService.listarLibros());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    //@PostMapping("/libro")
    //public ResponseEntity<Map<String,String>> agregarLibro(@RequestBody Libro libro){
    //    Map<String,String> response = new HashMap<>();
    //    try {
    //        libroService.guardarLibro(libro);
    //        response.put("message", "Libro creado con exito");
    //    } catch (Exception e) {
    //        response.put("Error", "");
    //
     //   }
    //}

}
