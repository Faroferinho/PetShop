package org.example.documents;

import lombok.Getter;
import lombok.Setter;
import org.example.documents.dto.FarmDTO;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter
public class Farm extends Animal{
    private String farm;

    public Farm(){

    }

    public Farm(String id, String name, String species, String age, char size, String comments, String farm){
        super(id, name, species, age, size, comments);
        this.farm = farm;
    }

    public Farm(FarmDTO dto){
        super(dto);
        farm = dto.getFarm();
    }
}
