package com.estebancano.webapp.biblioteca.service;

import java.util.List;

import com.estebancano.webapp.biblioteca.model.Empleado;

public interface IEmpleadoService {

    public List<Empleado> listarEmpleados();

    public void guardarEmpleado(Empleado empleado);

    public Empleado buscarEmpleadoPorId(Long Id);

    public void eliminarEmpleado(Empleado empleado);
}