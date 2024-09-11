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

import com.estebancano.webapp.biblioteca.model.Empleado;
import com.estebancano.webapp.biblioteca.service.EmpleadoService;

@Controller
@RestController
@RequestMapping("empleado")
@CrossOrigin(value = "http://127.0.0.1:5500")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    //Listar
    @GetMapping("/")
    public List<Empleado> listarEmpleados(){
        return empleadoService.listarEmpleados();
    }

    //Buscar
    @GetMapping("/id={id}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable Long id){
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            return ResponseEntity.ok(empleado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Agregar
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> agregarEmpleado(@RequestBody Empleado empleado){
        Map<String, String> response = new HashMap<>();
        try{
            if(!empleadoService.verificarDpiDuplicado(empleado)){
                empleadoService.guardarEmpleado(empleado);
                response.put("Message","Empleado agregado con exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "Error");
                response.put("err", "Hubo un error al intentar modificar el cliente");
                return ResponseEntity.badRequest().body(response);

            }
        }catch(Exception e){
            response.put("message", "Error");
            response.put("err", "Hubo un error al intentar modificar el cliente");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoNuevo){
        Map<String, String> response = new HashMap<>();
        
        try {
            Empleado empleadoAntiguo = empleadoService.buscarEmpleadoPorId(id);    
            empleadoAntiguo.setNombre(empleadoNuevo.getNombre());
            empleadoAntiguo.setApellido(empleadoNuevo.getApellido());
            empleadoAntiguo.setTelefono(empleadoNuevo.getTelefono());
            empleadoAntiguo.setDireccion(empleadoNuevo.getDireccion());
            empleadoAntiguo.setDpi(empleadoNuevo.getDpi());
            empleadoService.guardarEmpleado(empleadoAntiguo);
            response.put("message", "El Empleado se ha modificado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al intentar modificar el empleado");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarEmpleado(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            empleadoService.eliminarEmpleado(empleado);
            response.put("message", "El empleado se ha eliminado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "El empleado no se ha eliminado");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
