package com.alura.LiterAlura.repository;

import com.alura.LiterAlura.model.database.AuthorDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepositoory extends JpaRepository<AuthorDB, Long> {
}
