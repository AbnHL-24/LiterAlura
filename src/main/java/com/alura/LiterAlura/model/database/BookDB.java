package com.alura.LiterAlura.model.database;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "books")
public class BookDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer idGutendex;
    private String title;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubjectDB> subjects;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PersonDB> authors;

}
