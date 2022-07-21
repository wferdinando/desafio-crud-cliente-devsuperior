package io.github.wferdinando.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.wferdinando.desafio.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
