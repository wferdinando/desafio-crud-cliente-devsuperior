package io.github.wferdinando.desafio.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.wferdinando.desafio.entities.Client;
import io.github.wferdinando.desafio.servicies.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResouce {

    private final ClientService service;

    public ClientResouce(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        List<Client> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable(value = "id") Long id) {
        Client client = service.findById(id);
        return ResponseEntity.ok().body(client);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
