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

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(UserDTO dto){
        name = dto.getName();
        email = dto.getEmail();
        password = getPassword();
    }
}
