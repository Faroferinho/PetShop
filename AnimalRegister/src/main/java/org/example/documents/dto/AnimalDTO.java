package org.example.documents.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AnimalDTO {
    private String name;
    private String species;
    private String age;
    private char size;
    private String comments;
}
