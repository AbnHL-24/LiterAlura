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

    private String obtenerDatos(String url) {
        HttpClient client = HttpClient.newHttpClient().newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        //System.out.println(json);
        return json;
    }

    public String obtenerLibros(String libro) {
        String urlBaseLibros = "https://gutendex.com/books?search=";
        return obtenerDatos(urlBaseLibros + libro.replace(" ", "%20"));
    }
}
