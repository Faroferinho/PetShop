package org.example.documents;

import lombok.Getter;
import lombok.Setter;
import org.example.documents.DTO.ClientDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter @Setter
public class Client extends User{

    public Client(){
        super();
    }

    public Client(String id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public Client(ClientDTO dto){
        super(dto);
    }
}
