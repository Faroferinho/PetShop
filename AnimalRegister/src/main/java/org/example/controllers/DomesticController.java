package org.example.controllers;

import org.example.documents.Domestic;
import org.example.documents.dto.DomesticDTO;
import org.example.safety.Constants;
import org.example.services.DomesticServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.lang.constant.Constable;
import java.util.List;
import java.util.Optional;

@RestController
public class DomesticController implements CRUDController<Domestic, DomesticDTO> {
    @Autowired
    public DomesticServices services;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Override
    @PostMapping(Constants.DOMESTIC)
    public ResponseEntity<Domestic> create(@RequestHeader(value = "Authorization") String token, @RequestBody DomesticDTO domesticDTO) {
        try{
            headers.set("Content-Type", "application/json");
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            System.out.println("Validate = " + Constants.VALIDATE);

            ResponseEntity<Boolean> response = restTemplate.exchange(
                    Constants.VALIDATE,
                    HttpMethod.GET,
                    entity,
                    Boolean.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Domestic d = services.save(domesticDTO);

                return ResponseEntity.status(HttpStatus.CREATED).body(d);
            }
        }catch (Exception e){
            System.err.println("Error trying to validate User\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect User or Password");
    }

    @Override
    @GetMapping(Constants.DOMESTIC)
    public ResponseEntity<List<Domestic>> findAll(@RequestHeader(value = "Authorization") String token) {
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
            System.err.println("Error trying to validade user\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect Authorization");
    }

    @Override
    @GetMapping(Constants.DOMESTIC + "/{id}")
    public ResponseEntity<Optional<Domestic>> findById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
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
            System.err.println("Error trying to validade user\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect Authorization");
    }

    @Override
    @DeleteMapping(Constants.DOMESTIC + "/{id}")
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
            System.err.println("Error trying to validade user\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect Authorization");
    }
}
