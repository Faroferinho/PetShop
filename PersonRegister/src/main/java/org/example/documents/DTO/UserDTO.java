package org.example.documents.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class UserDTO {
    private String name;
    private String email;
    private String password;
}
