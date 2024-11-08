package com.pablo.microServ.repository;

import com.pablo.microServ.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
