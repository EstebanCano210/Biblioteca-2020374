package com.estebancano.webapp.biblioteca.service;

import java.util.List;

import com.estebancano.webapp.biblioteca.model.Cliente;

public interface IClienteService {
    
    public List<Cliente> listarClientes();

    public void guardarCliente(Cliente cliente);

    public Cliente buscarClientePorId(Long Id);

    public void eliminarCliente(Cliente cliente);
}
