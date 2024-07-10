package com.alura.LiterAlura.service;

import com.alura.LiterAlura.model.database.BookDB;
import com.alura.LiterAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookDB saveBook(BookDB bookDB) {
        return bookRepository.save(bookDB);
    }
}
