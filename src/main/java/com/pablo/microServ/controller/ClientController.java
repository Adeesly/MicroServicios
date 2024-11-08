package com.pablo.microServ.controller;

import com.pablo.microServ.entity.Client;
import com.pablo.microServ.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/creacliente")
    public ResponseEntity<Client> createCliente(@RequestBody Client client) {
        Client newClient = clientService.createClient(client);
        return ResponseEntity.ok(newClient);
    }

    @GetMapping("/kpiclientes")
    public ResponseEntity<String> getKPIClientes() {
        double averageAge = clientService.calculateAverageAge();
        double standardDeviation = clientService.calculateAgeStandardDeviation();
        String kpi = String.format("Promedio de edad: %.2f, Desviación estándar: %.2f", averageAge, standardDeviation);
        return ResponseEntity.ok(kpi);
    }

    @GetMapping("/listclientes")
    public ResponseEntity<List<Client>> listClients() {
        List<Client> clients = clientService.listClients();
        return ResponseEntity.ok(clients);
    }
}