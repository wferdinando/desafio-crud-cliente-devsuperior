package io.github.wferdinando.desafio.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.wferdinando.desafio.entities.Client;
import io.github.wferdinando.desafio.repositories.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(client.get());
    }

    public void delete(Long id) {
        Client client = clientRepository.getReferenceById(id);
        clientRepository.delete(client);
    }

}
