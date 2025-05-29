package org.example.controller;

import org.example.documents.LoginDTO;
import org.example.safety.Constants;
import org.example.safety.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;

@RestController
public class AuthenticationController {
    @Autowired
    private JWTTokenProvider provider;

    private RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @PostMapping(Constants.LOGIN)
    public String validateUser(@RequestBody LoginDTO dto){
        try{
            headers.set("Content-Type", "application/json");

            String body =
                    "{\n" +
                    "  \"email\" : \"" + dto.getEmail() + "\",\n" +
                    "  \"password\" : \"" + dto.getPassword() + "\"\n" +
                    "}";
            System.out.println(body);

            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            ResponseEntity<Boolean> response = restTemplate.exchange(
                    Constants.CLIENTS,
                    HttpMethod.POST,
                    entity,
                    Boolean.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
                return provider.generateToken(dto.getEmail());
            }
        }catch (Exception e){
            System.err.println("Error trying to validate User\n" + e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect User or Password");
    }
}
