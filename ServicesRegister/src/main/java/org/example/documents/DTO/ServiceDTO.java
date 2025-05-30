package org.example.documents.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
public abstract class ServiceDTO {
    private String type;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private BigDecimal value;
    private String comment;
}
