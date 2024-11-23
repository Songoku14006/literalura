package com.alura.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") Integer downloadCount,
        @JsonAlias("languages") List<String> linguagens,
        @JsonAlias("authors") List<Autor> autores
) {
    public record Autor(
            @JsonAlias("name") String nome,
            @JsonAlias("birth_year") Integer anoNascimento,
            @JsonAlias("death_year") Integer anoMorte
    ) {}
}