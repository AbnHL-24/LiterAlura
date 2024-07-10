package com.alura.LiterAlura.model.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ListOfBooksAPI(
        @JsonAlias("count") Integer count,
        @JsonAlias("next") String next,
        @JsonAlias("previous") String previous,
        @JsonAlias("results") List<BookAPI> results
) {
}
