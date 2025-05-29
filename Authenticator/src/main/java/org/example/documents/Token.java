package org.example.documents;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Token {
    private String message;
    private String token;

    public Token(String message, String token){
        this.message = message;
        this.token = token;
    }
}
