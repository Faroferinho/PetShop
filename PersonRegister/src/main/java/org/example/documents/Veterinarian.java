package org.example.documents;

import lombok.Getter;
import lombok.Setter;
import org.example.documents.DTO.VeterinarianDTO;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter
public class Veterinarian extends User{
    private String phone;

    public Veterinarian(String name, String email, String password, String phone) {
        super(name, email, password);
        this.phone = phone;
    }

    public Veterinarian(VeterinarianDTO dto){
        super(dto);
        phone = dto.getPhone();
    }
}
