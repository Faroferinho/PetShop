package org.example.services;

import org.example.documents.Client;
import org.example.documents.DTO.ClientDTO;
import org.example.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements CRUDServices<Client, ClientDTO> {
    @Autowired
    private ClientRepository repository;

    @Override
    public Client save(ClientDTO clientDTO) {
        return repository.save(new Client(clientDTO));
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Client> findByID(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteByID(String id) {
        repository.deleteById(id);
    }
}
