package org.example.controllers;

import org.example.documents.Client;
import org.example.documents.DTO.ClientDTO;
import org.example.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController implements CRUDController<Client, ClientDTO> {
    @Autowired
    public ClientService service;

    @Override
    public ResponseEntity<Client> create(String token, ClientDTO clientDTO) {
        Client c = service.save(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @Override
    public ResponseEntity<List<Client>> findAll(String token) {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<Optional<Client>> findById(String token, String id) {
        return ResponseEntity.ok(service.findByID(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(String token, String id) {
        service.deleteByID(id);
        return ResponseEntity.noContent().build();
    }
}
