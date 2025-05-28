package org.example.documents;

import lombok.Getter;
import lombok.Setter;
import org.example.documents.DTO.MaintenanceDTO;
import org.example.documents.DTO.ServiceDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter @Setter
public class Maintenance extends Service{
    public Maintenance(String id, String type, Date date, double value, String comment) {
        super(id, type, date, value, comment);
    }

    public Maintenance(MaintenanceDTO dto) {
        super(dto);
    }
}
