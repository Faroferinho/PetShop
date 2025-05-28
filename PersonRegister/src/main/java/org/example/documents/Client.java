package org.example.documents;

import lombok.Getter;
import lombok.Setter;
import org.example.documents.DTO.ClientDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter @Setter
public class Client extends User{
    private Date date;

    public Client(ClientDTO dto){
        super(dto);
        date = dto.getDate();
    }
}
