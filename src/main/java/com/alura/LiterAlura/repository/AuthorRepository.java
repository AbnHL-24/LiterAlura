package com.alura.LiterAlura.repository;

import com.alura.LiterAlura.model.database.AuthorDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorDB, Long> {
    List<AuthorDB> findByDeathYearGreaterThanEqualOrDeathYearIsNull(Integer year);
}
