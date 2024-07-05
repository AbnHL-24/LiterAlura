package com.alura.LiterAlura.model.database;

import com.alura.LiterAlura.model.api.PersonAPI;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "persons")
public class PersonDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer birthYear;
    private Integer deathYear;
    @Column(unique = true, nullable = false)
    private String name;

    public PersonDB() {};

    public PersonDB(PersonAPI personAPI) {
        this.birthYear = personAPI.birthYear();
        this.deathYear = personAPI.deathYear();
        this.name = personAPI.name();
    }
}
