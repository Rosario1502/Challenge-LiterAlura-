package com.catalogolibros.catalogo_libros.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GutendexService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GutendexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JsonNode buscarLibroPorTitulo(String titulo) {
        String url = UriComponentsBuilder.fromHttpUrl("https://gutendex.com/books/")
                .queryParam("search", titulo)
                .toUriString();

        String json = restTemplate.getForObject(url, String.class);
        return objectMapper.readTree(json);
    }
}
