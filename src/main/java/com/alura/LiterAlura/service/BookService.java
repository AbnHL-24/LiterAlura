package com.alura.LiterAlura.service;

import com.alura.LiterAlura.model.database.BookDB;
import com.alura.LiterAlura.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookDB saveBook(BookDB bookDB) {
        return bookRepository.save(bookDB);
    }

    public List<BookDB> listarLibrosRegistrados() {
        return bookRepository.findAll();
    }

    public List<BookDB> listarLibrosPorIdioma(String idioma) {
        return bookRepository.findByLanguage(idioma);
    }

    @Transactional
    public List<BookDB> listarLibrosPorIdiomaQuery(String idioma) {
        return bookRepository.findByLanguageWithAutores(idioma);
    }
}
