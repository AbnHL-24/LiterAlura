package com.alura.LiterAlura.model.database;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "authors")
public class AuthorDB extends PersonDB{
}
