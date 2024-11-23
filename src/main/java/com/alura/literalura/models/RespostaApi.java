package com.alura.literalura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaApi {
    private List<DadosLivro> results;

    public List<DadosLivro> getResults() {
        return results;
    }

    public void setResults(List<DadosLivro> results) {
        this.results = results;
    }
}
