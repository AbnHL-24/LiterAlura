package com.alura.LiterAlura.repository;

import com.alura.LiterAlura.model.database.BookDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookDB, Long> {
    List<BookDB> findByLanguage(String language);

    @Query("SELECT b FROM BookDB b LEFT JOIN FETCH b.libroAutorDB WHERE b.language = :language")
    List<BookDB> findByLanguageWithAutores(@Param("language") String language);
}
