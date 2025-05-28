package org.example.controllers;

import org.example.documents.Client;
import org.example.documents.DTO.ClientDTO;
import org.example.safety.Constants;
import org.example.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController implements CRUDController<Client, ClientDTO> {
    @Autowired
    public ClientService service;

    @Override
    @PostMapping(Constants.CLIENT)
    public ResponseEntity<Client> create(@RequestHeader(value = "Authorization") String token, @RequestBody ClientDTO clientDTO) {
        Client c = service.save(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @Override
    @GetMapping(Constants.CLIENT)
    public ResponseEntity<List<Client>> findAll(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @GetMapping(Constants.CLIENT + "{id}")
    public ResponseEntity<Optional<Client>> findById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
        return ResponseEntity.ok(service.findByID(id));
    }

    @Override
    @DeleteMapping(Constants.CLIENT + "{id}")
    public ResponseEntity<Void> deleteById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
        service.deleteByID(id);
        return ResponseEntity.noContent().build();
    }
}
