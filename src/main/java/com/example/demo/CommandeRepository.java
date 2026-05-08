package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande , Long> {
    //SELECT * FROM commande WHERE client_id = ?
    List<Commande> findByClientId(Long clientId);
}
