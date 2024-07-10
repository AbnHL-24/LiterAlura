package com.alura.LiterAlura.service;

import com.alura.LiterAlura.model.database.LibroAutorDB;
import com.alura.LiterAlura.repository.LibroAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroAutorService {
    @Autowired
    private LibroAutorRepository libroAutorRepository;

    public LibroAutorDB saveLibroAutor(LibroAutorDB libroAutor) {
        return libroAutorRepository.save(libroAutor);
    }
}
