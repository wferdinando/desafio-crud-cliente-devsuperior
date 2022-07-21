package io.github.wferdinando.desafio.servicies;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import io.github.wferdinando.desafio.entities.Client;
import io.github.wferdinando.desafio.entities.dto.ClientDTO;
import io.github.wferdinando.desafio.repositories.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> findAll() {
        List<Client> listCli = clientRepository.findAll();
        List<ClientDTO> listaCliDTO = listCli.stream().map(c -> new ClientDTO(c)).collect(Collectors.toList());
        return listaCliDTO;
    }

    public ClientDTO findById(Long id) {
        Optional<Client> obj = clientRepository.findById(id);
        Client client = obj.orElseThrow(() -> new EntityNotFoundException("Entity Not Found!"));
        return new ClientDTO(client);
    }

    public void delete(Long id) {
        Client client = clientRepository.getReferenceById(id);
        clientRepository.delete(client);
    }

}
