package com.alura.LiterAlura.repository;

import com.alura.LiterAlura.model.database.LibroAutorDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroAutorRepository extends JpaRepository<LibroAutorDB, Long> {
}
