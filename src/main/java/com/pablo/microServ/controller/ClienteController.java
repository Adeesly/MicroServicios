package com.pablo.microServ.controller;

import com.pablo.microServ.entity.Cliente;
import com.pablo.microServ.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/creacliente")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @GetMapping("/kpiclientes")
    public ResponseEntity<String> obtenerKPIClientes() {
        double promedioEdad = clienteService.calcularPromedioEdad();
        double desviacionEstandar = clienteService.calcularDesviacionEstandarEdad();
        String kpi = String.format("Promedio de edad: %.2f, Desviación estándar: %.2f", promedioEdad, desviacionEstandar);
        return ResponseEntity.ok(kpi);
    }

    @GetMapping("/listaclientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }
}