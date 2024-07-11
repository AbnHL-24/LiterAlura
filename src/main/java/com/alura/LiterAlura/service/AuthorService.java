package com.alura.LiterAlura.service;

import com.alura.LiterAlura.model.database.AuthorDB;
import com.alura.LiterAlura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorDB saveAuthor(AuthorDB author) {
        return authorRepository.save(author);
    }

    public List<AuthorDB> listarAutoresRegistrados() {
        return authorRepository.findAll();
    }

    public List<AuthorDB> listarAutoresVivosEnAno(Integer year) {
        return authorRepository.findByDeathYearGreaterThanEqualOrDeathYearIsNull(year);
    }
}
