package com.alura.LiterAlura.model.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookAPI(
        @JsonAlias("id") Integer id,
        @JsonAlias("title") String title,
        @JsonAlias("subjects")List<String> subjects,
        @JsonAlias("authors") List<PersonAPI> authors,
        @JsonAlias("translators") List<PersonAPI> translators,
        @JsonAlias("bookshelves") List<String> bookshelves,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("copyright") Boolean copyright,
        @JsonAlias("media_type") String mediaType,
        @JsonAlias("formats") Map<String, String> formats,
        @JsonAlias("download_count") Integer downloadCount
        ) {
}
