package com.alura.LiterAlura.repository;

import com.alura.LiterAlura.model.database.BookDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookDB, Long> {

}
