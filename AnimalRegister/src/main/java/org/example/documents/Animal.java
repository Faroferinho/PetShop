package org.example.documents;

import lombok.Getter;
import lombok.Setter;
import org.example.documents.dto.AnimalDTO;

@Getter @Setter
public abstract class Animal {
    private String id;
    private String name;
    private String species;
    private int age;
    private char size;
    private String comments;

    public Animal(){

    }

    public Animal(String id, String name, String species, int age, char size, String comments){
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.size = size;
        this.comments = comments;
    }

    public Animal(AnimalDTO dto){
        name = dto.getName();
        species = dto.getSpecies();
        age = dto.getAge();
        size = dto.getSize();
        comments = dto.getComments();
    }
}
