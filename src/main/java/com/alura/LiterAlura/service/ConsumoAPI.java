package com.alura.LiterAlura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    private static ConsumoAPI instance;

    private ConsumoAPI() {}
    public static ConsumoAPI getInstance() {
        if (instance == null) {
            instance = new ConsumoAPI();
        }
        return instance;
    }

    public String obtenerDatos(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }

    public String obtenerLibros(String libro) {
        String urlBaseLibros = "gutendex.com/books?search=";
        return obtenerDatos(urlBaseLibros + libro.replace(" ", "%20"));
    }
}
