package com.alura.literalura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(name = "linguagens") // Agora linguagens será uma coluna simples na tabela livros
    private String linguagens;  // Alterado de List<String> para String (para armazenar como uma string única, separada por vírgula ou outro delimitador)

    private Integer downloadCount;

    private String nomeAutor;
    private Integer anoNascimento;
    private Integer anoMorte;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.downloadCount = dadosLivro.downloadCount();

        // Concatena as linguagens em uma string
        this.linguagens = String.join(", ", dadosLivro.linguagens()); // Exemplo de como armazenar as linguagens separadas por vírgula

        if (dadosLivro.autores() != null && !dadosLivro.autores().isEmpty()) {
            var autor = dadosLivro.autores().get(0);
            this.nomeAutor = autor.nome();
            this.anoNascimento = autor.anoNascimento();
            this.anoMorte = autor.anoMorte();
        } else {
            this.nomeAutor = "Desconhecido";
            this.anoNascimento = null;
            this.anoMorte = null;
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLinguagens() {
        return linguagens;
    }

    public void setLinguagens(String linguagens) {
        this.linguagens = linguagens;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' +
                ", linguagens='" + linguagens + '\'' +
                ", downloadCount=" + downloadCount +
                ", nomeAutor='" + nomeAutor + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", anoMorte=" + anoMorte;
    }
}