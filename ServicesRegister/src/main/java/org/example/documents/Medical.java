package org.example.documents;

import lombok.Getter;
import lombok.Setter;
import org.example.documents.DTO.MedicalDTO;
import org.example.documents.DTO.ServiceDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter @Setter
public class Medical extends Service{
    private String veterinarian;

    public Medical(MedicalDTO dto) {
        super(dto);
        veterinarian = dto.getVeterinarian();
    }

    public Medical(String id, String type, Date date, double value, String comment, String veterinarian) {
        super(id, type, date, value, comment);
        this.veterinarian = veterinarian;
    }
}
