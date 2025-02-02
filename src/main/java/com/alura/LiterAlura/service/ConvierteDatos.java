package com.alura.LiterAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper objectMapper = new ObjectMapper();
    private static ConvierteDatos instance;

    private ConvierteDatos(){}

    public static ConvierteDatos getInstance(){
        if(instance == null){
            instance = new ConvierteDatos();
        }
        return instance;
    }

    @Override
    public <T> T obtenerDatos(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
