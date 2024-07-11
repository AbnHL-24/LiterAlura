package com.alura.LiterAlura.model.database;

import com.alura.LiterAlura.model.api.BookAPI;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
public class BookDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer idGutendex;
    private String title;
    private String language;
    @OneToMany(mappedBy = "bookDB", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<LibroAutorDB> libroAutorDB;
    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LibroAutor> libroAutores;*/

    public BookDB() {}

    public BookDB(BookAPI bookAPI) {
        this.idGutendex = bookAPI.id();
        this.title = bookAPI.title();
        this.language = bookAPI.languages().getFirst();
    }
}
