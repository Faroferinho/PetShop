package org.example.documents;

import lombok.Getter;
import lombok.Setter;
import org.example.documents.dto.ZooDTO;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter
public class Zoo extends Animal{
    private String zoo;

    public Zoo(){

    }

    public Zoo(String id, String name, String species, String age, char size, String comments, String zoo){
        super(id, name, species, age, size, comments);
        this.zoo = zoo;
    }

    public Zoo(ZooDTO dto){
        super(dto);
        zoo = dto.getZoo();
    }
}
