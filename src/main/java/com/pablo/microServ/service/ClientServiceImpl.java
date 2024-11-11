package com.pablo.microServ.service;

import com.pablo.microServ.DTO.ClientDTO;
import com.pablo.microServ.entity.Client;
import com.pablo.microServ.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<ClientDTO> listClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(this::convertToClientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateAverageAge() {
        OptionalDouble average = clientRepository.findAll().stream()
                .mapToInt(this::calculateAge)
                .average();
        return average.orElse(0);
    }

    @Override
    public double calculateAgeStandardDeviation() {
        List<Integer> ages = clientRepository.findAll().stream()
                .map(this::calculateAge)
                .toList();
        double average = ages.stream().mapToInt(age -> age).average().orElse(0);
        double variance = ages.stream()
                .mapToDouble(age -> Math.pow(age - average, 2))
                .average()
                .orElse(0);
        return Math.sqrt(variance);
    }

    private int calculateAge(Client client) {
        if (client.getBirthDate() == null) {
            return 0;
        }
        return Period.between(client.getBirthDate(), LocalDate.now()).getYears();
    }

    private ClientDTO convertToClientDTO(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getLastName(),
                client.getAge(),
                client.getBirthDate()
        );
    }
}
