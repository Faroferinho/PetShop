package org.example.controllers;

import org.example.documents.DTO.MaintenanceDTO;
import org.example.documents.Maintenance;
import org.example.safety.Constants;
import org.example.services.MaintenanceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class MaintenanceController implements CRUDController<Maintenance, MaintenanceDTO> {
    @Autowired
    private MaintenanceServices services;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Override
    @PostMapping(Constants.MAINTENANCE)
    public ResponseEntity<Maintenance> create(@RequestHeader(value = "Authorization") String token, @RequestBody MaintenanceDTO maintenanceDTO) {
        System.out.println("Maintanace:\n" + maintenanceDTO.getType() + " | " + maintenanceDTO.getComment() + " | " + maintenanceDTO.getDate() + " | " + maintenanceDTO.getValue());

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

            System.out.println("Token:" + token + " \n");

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Maintenance m = services.save(maintenanceDTO);

                return ResponseEntity.status(HttpStatus.CREATED).body(m);
            }
        }catch (Exception e){
            System.err.println("Error trying to validate User\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect User or Password");
    }

    @Override
    @GetMapping(Constants.MAINTENANCE)
    public ResponseEntity<List<Maintenance>> findAll(@RequestHeader(value = "Authorization") String token) {
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
                return ResponseEntity.ok(services.findAll());
            }
        }catch (Exception e){
            System.err.println("Error trying to validate User\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect User or Password");
    }

    @Override
    @GetMapping(Constants.MAINTENANCE + "/{id}")
    public ResponseEntity<Optional<Maintenance>> findById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
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
                return ResponseEntity.ok(services.findByID(id));
            }
        }catch (Exception e){
            System.err.println("Error trying to validate User\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect User or Password");
    }

    @Override
    @DeleteMapping(Constants.MAINTENANCE + "/{id}")
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
                services.deleteByID(id);
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
            System.err.println("Error trying to validate User\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect User or Password");
    }
}
