package org.example.documents;

import lombok.Getter;
import lombok.Setter;
import org.example.documents.dto.DomesticDTO;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter
public class Domestic extends Animal{
    public String master;

    public Domestic(){
        super();
    }

    public Domestic(String id, String name, String species, int age, char size, String comments, String master){
        super(id, name, species, age, size, comments);
        this.master = master;
    }

    public Domestic(DomesticDTO dto){
        super(dto);
        master = dto.getMaster();
    }
}
