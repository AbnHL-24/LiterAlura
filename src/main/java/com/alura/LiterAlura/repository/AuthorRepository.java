package com.alura.LiterAlura.repository;

import com.alura.LiterAlura.model.database.AuthorDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorDB, Long> {
}
