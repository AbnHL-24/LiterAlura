package com.alura.LiterAlura.model.database;

import com.alura.LiterAlura.model.api.PersonAPI;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "authors")
public class AuthorDB{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer birthYear;
    private Integer deathYear;
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "autorDB", cascade = CascadeType.ALL)
    private List<LibroAutor> libroAutores;

    public AuthorDB() {}

    public AuthorDB(PersonAPI personAPI){
        this.birthYear = personAPI.birthYear();
        this.deathYear = personAPI.deathYear();
        this.name = personAPI.name();
        // TODO agregar lógica del libroAutor.
    }
}
