package com.alura.LiterAlura.model.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PersonApi(
        @JsonAlias("birth_year") Integer birthYear,
        @JsonAlias("death_year") Integer deathYear,
        @JsonAlias("name") String name
) {
}
