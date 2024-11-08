package com.pablo.microServ.service;

import com.pablo.microServ.entity.Cliente;
import com.pablo.microServ.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public double calcularPromedioEdad() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().mapToInt(Cliente::getAge).average().orElse(0);
    }

    public double calcularDesviacionEstandarEdad() {
        List<Cliente> clientes = clienteRepository.findAll();
        double promedio = calcularPromedioEdad();
        return Math.sqrt(clientes.stream().mapToDouble(cliente -> Math.pow(cliente.getAge() - promedio, 2)).sum() / clientes.size());
    }
}