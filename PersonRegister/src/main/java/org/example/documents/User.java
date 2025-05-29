package org.example.documents;

import lombok.Getter;
import lombok.Setter;
import org.example.documents.DTO.UserDTO;

@Getter @Setter
public abstract class User {
    private String id;
    private String name;
    private String email;
    private String password;

    public User(){

    }

    public User(String id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(UserDTO dto){
        name = dto.getName();
        email = dto.getEmail();
        password = dto.getPassword();
        System.out.println("Salvar Senha:" + dto.getPassword() + " | " + password);
    }
}
