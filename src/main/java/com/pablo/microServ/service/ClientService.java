package com.pablo.microServ.service;

import com.pablo.microServ.entity.Client;
import com.pablo.microServ.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    public double calculateAverageAge() {
        List<Client> clientes = clientRepository.findAll();
        return clientes.stream().mapToInt(Client::getAge).average().orElse(0);
    }

    public double calculateAgeStandardDeviation() {
        List<Client> clientes = clientRepository.findAll();
        double promedio = calculateAverageAge();
        return Math.sqrt(clientes.stream().mapToDouble(cliente -> Math.pow(cliente.getAge() - promedio, 2)).sum() / clientes.size());
    }
}