package com.pablo.microServ.service;

import com.pablo.microServ.DTO.ClientDTO;
import com.pablo.microServ.entity.Client;

import java.util.List;


public interface ClientService {

    public Client createClient(Client client);
    public List<ClientDTO> listClients();
    public double calculateAverageAge();
    public double calculateAgeStandardDeviation();
}