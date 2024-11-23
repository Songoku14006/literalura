package com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // Segue redirecionamentos
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .header("Accept", "application/json") // Garantir formato JSON
                .header("User-Agent", "JavaHttpClient")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Verifica o código de status
            if (response.statusCode() != 200) {
                System.out.println("Erro na requisição. Código de status: " + response.statusCode());
                return null;
            }

            String json = response.body();
            return json;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro na comunicação com a API", e);
        }
    }
}