package com.pablo.microServ.controller;

import com.pablo.microServ.DTO.ClientDTO;
import com.pablo.microServ.entity.Client;
import com.pablo.microServ.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/creacliente") // Manteniendo el endpoint específico
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client newClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient); // 201 Created
    }

    @GetMapping("/kpiclientes")
    public ResponseEntity<String> getKPIClientes() {
        double averageAge = clientService.calculateAverageAge();
        double standardDeviation = clientService.calculateAgeStandardDeviation();
        String kpi = String.format("Promedio de edad: %.2f, Desviación estándar: %.2f", averageAge, standardDeviation);
        return ResponseEntity.ok(kpi);
    }

    @GetMapping("/listclientes")
    public ResponseEntity<List<ClientDTO>> listClients() {
        List<ClientDTO> clients = clientService.listClients();
        return ResponseEntity.ok(clients);
    }

}