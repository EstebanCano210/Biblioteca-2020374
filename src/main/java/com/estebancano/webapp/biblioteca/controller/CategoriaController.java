package com.estebancano.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estebancano.webapp.biblioteca.model.Categoria;
import com.estebancano.webapp.biblioteca.service.CategoriaService;

@Controller
@RestController
@RequestMapping("categoria")
@CrossOrigin(value = "http://127.0.0.1:5500")//permite la conexion a otro servidor
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    //Listar
    @GetMapping("/")
    public List<Categoria> listarCategorias(){
        return categoriaService.listarCategorias();
    }

    //Buscar
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id){
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Agregar
    @PostMapping("/")
    public ResponseEntity<Map<String, Boolean>> agregarCategoria(@RequestBody Categoria categoria){
        Map<String, Boolean> response = new HashMap<>();
        try{
            if (!categoriaService.verificarCategoriaDuplicada(categoria)) {
                categoriaService.guardarCategoria(categoria);
                response.put("Categoria creada con exito", Boolean.TRUE);
                return ResponseEntity.ok(response);
            }else{
                response.put("Esta Categoria ya ha sido creada", Boolean.FALSE);
                return ResponseEntity.badRequest().body(response);
            }
        }catch(Exception e){
            response.put("No se ha creado la categoria" , Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaNueva){
        Map<String, String> response = new HashMap<>();
        
        try {
            
                Categoria categoriaVieja = categoriaService.buscarCategoriaPorId(id);    
                categoriaVieja.setNombreCategoria(categoriaNueva.getNombreCategoria());
                categoriaService.guardarCategoria(categoriaVieja);
                response.put("message", "La categoria se ha modificado con exito");
                return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al intentar modificar la categoria");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);
            response.put("message", "La categoria se ha eliminado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "La categoria no se ha eliminado con exito");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
