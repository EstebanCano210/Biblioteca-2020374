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

import com.estebancano.webapp.biblioteca.model.Cliente;
import com.estebancano.webapp.biblioteca.service.ClienteService;

@Controller
@RestController
@RequestMapping("cliente")
@CrossOrigin(value = "http://127.0.0.1:5500")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //Listar
    @GetMapping("/")
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    //Buscar
    @GetMapping("/dpi={dpi}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long dpi){
        try {
            Cliente cliente = clienteService.buscarClientePorId(dpi);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Agregar
    @PostMapping("/")
    public ResponseEntity<Map<String, Boolean>> agregarCliente(@RequestBody Cliente cliente){
        Map<String, Boolean> response = new HashMap<>();
        try{
            clienteService.guardarCliente(cliente);
            response.put("Cliente agregado con exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("Cliente agregado con exito", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar
    @PutMapping("/{dpi}")
    public ResponseEntity<Map<String, String>> editarCliente(@PathVariable Long dpi, @RequestBody Cliente clienteNuevo){
        Map<String, String> response = new HashMap<>();
        
        try {
            Cliente clienteAntiguo = clienteService.buscarClientePorId(dpi);    
            clienteAntiguo.setNombre(clienteNuevo.getNombre());
            clienteAntiguo.setApellido(clienteNuevo.getApellido());
            clienteAntiguo.setTelefono(clienteNuevo.getTelefono());
            clienteService.guardarCliente(clienteAntiguo);
            response.put("message", "El cliente se ha modificado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al intentar modificar el cliente");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Eliminar
    @DeleteMapping("/{dpi}")
    public ResponseEntity<Map<String, String>> eliminarCliente(@PathVariable Long dpi){
        Map<String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorId(dpi);
            clienteService.eliminarCliente(cliente);
            response.put("message", "El cliente se ha eliminado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "El cliente no se ha eliminado");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
