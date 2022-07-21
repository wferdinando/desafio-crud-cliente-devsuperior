package io.github.wferdinando.desafio.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.wferdinando.desafio.entities.Client;
import io.github.wferdinando.desafio.entities.dto.ClientDTO;
import io.github.wferdinando.desafio.repositories.ClientRepository;
import io.github.wferdinando.desafio.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> list = clientRepository.findAll(pageRequest);
        return list.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> obj = clientRepository.findById(id);
        Client client = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found!"));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO cliDto) {
        cliDto.setId(null);
        Client client = new Client();
        copyDtoToEntity(cliDto, client);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO cliDto) {
        try {
            cliDto.setId(id);
            Client client = clientRepository.getReferenceById(id);
            copyDtoToEntity(cliDto, client);
            client = clientRepository.save(client);

            return new ClientDTO(client);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        Client client = clientRepository.getReferenceById(id);
        clientRepository.delete(client);
    }

    public void copyDtoToEntity(ClientDTO cliDto, Client client) {
        client.setName(cliDto.getName());
        client.setCpf(cliDto.getCpf());
        client.setBirthDate(cliDto.getBirthDate());
        client.setIncome(cliDto.getIncome());
        client.setChildren(cliDto.getChildren());
    }

}
