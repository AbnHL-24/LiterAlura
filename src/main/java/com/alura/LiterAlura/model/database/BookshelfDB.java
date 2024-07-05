package com.alura.LiterAlura.model.database;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bookshelves")
public class BookshelfDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bookShelf;
    @ManyToOne
    private BookDB book;
}
