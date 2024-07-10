package com.alura.LiterAlura.model.database;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LibroAutorDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private BookDB bookDB;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AuthorDB authorDB;

    public LibroAutorDB() {}

    public LibroAutorDB(BookDB bookDB, AuthorDB authorDB) {
        this.bookDB = bookDB;
        this.authorDB = authorDB;
    }
}
