package org.example.controllers;

import org.example.documents.Client;
import org.example.documents.DTO.ClientDTO;
import org.example.documents.DTO.LoginDTO;
import org.example.safety.Constants;
import org.example.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController implements CRUDController<Client, ClientDTO> {
    @Autowired
    public ClientService service;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Override
    public ResponseEntity<Client> create(@RequestHeader(value = "Authorization") String token, @RequestBody ClientDTO clientDTO) {
        try{
            headers.set("Content-Type", "application/json");

            HttpEntity<Boolean> entity = new HttpEntity<>(headers);

            ResponseEntity<Boolean> response = restTemplate.exchange(
                    Constants.VALIDATE,
                    HttpMethod.GET,
                    entity,
                    Boolean.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Client c = service.save(clientDTO);

                return ResponseEntity.status(HttpStatus.CREATED).body( new Client(clientDTO) );
            }
        }catch (Exception e){
            System.err.println("Error trying to validate User\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect User or Password");
    }

    @PostMapping(Constants.CLIENT)
    public ResponseEntity<Client> create(@RequestBody ClientDTO clientDTO) {
        Client c = service.save(clientDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body( new Client(clientDTO) );
    }


    @Override
    @GetMapping(Constants.CLIENT)
    public ResponseEntity<List<Client>> findAll(@RequestHeader(value = "Authorization") String token) {
        try{
            headers.set("Content-Type", "application/json");
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<Boolean> entity = new HttpEntity<>(headers);

            ResponseEntity<Boolean> response = restTemplate.exchange(
                    Constants.VALIDATE,
                    HttpMethod.GET,
                    entity,
                    Boolean.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return ResponseEntity.ok(service.findAll());
            }
        }catch (Exception e){
            System.err.println("Error trying to validate User\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect User or Password");
    }

    @Override
    @GetMapping(Constants.CLIENT + "/{id}")
    public ResponseEntity<Optional<Client>> findById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
        try{
            headers.set("Content-Type", "application/json");
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<Boolean> entity = new HttpEntity<>(headers);

            ResponseEntity<Boolean> response = restTemplate.exchange(
                    Constants.VALIDATE,
                    HttpMethod.GET,
                    entity,
                    Boolean.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return ResponseEntity.ok(service.findByID(id));
            }
        }catch (Exception e){
            System.err.println("Error trying to validate User\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect User or Password");
    }

    @Override
    @DeleteMapping(Constants.CLIENT + "/{id}")
    public ResponseEntity<Void> deleteById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
        try{
            headers.set("Content-Type", "application/json");
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<Boolean> entity = new HttpEntity<>(headers);

            ResponseEntity<Boolean> response = restTemplate.exchange(
                    Constants.VALIDATE,
                    HttpMethod.GET,
                    entity,
                    Boolean.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                service.deleteByID(id);
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
            System.err.println("Error trying to validate User\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect User or Password");
    }

    @PostMapping(Constants.LOGIN)
    public ResponseEntity<Boolean> login(@RequestBody LoginDTO dto){
        System.out.println("Login:\n" + dto.getEmail() + " | " + dto.getPassword());
        if (service.validateUser(dto.getEmail(), dto.getPassword())){
            return ResponseEntity.ok(true);
        }
        return null;
    }
}
