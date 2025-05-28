package org.example.documents;

import lombok.Getter;
import lombok.Setter;
import org.example.documents.DTO.ServiceDTO;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
public abstract class Service {
    private String id;
    private String type;
    private Date date;
    private BigDecimal value;
    private String comment;

    public Service(String id, String type, Date date, double value, String comment){
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = new BigDecimal(value);
        this.comment = comment;
    }

    public Service(ServiceDTO dto){
        type = dto.getType();
        date = dto.getDate();
        value = dto.getValue();
        comment = dto.getComment();
    }
}
