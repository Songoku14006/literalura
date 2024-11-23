package com.alura.literalura.repository;

import com.alura.literalura.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTituloContainingIgnoreCase(String nomeTitulo);

    @Query("SELECT l.nomeAutor FROM Livro l")
    List<String> listAutors();

    @Query("SELECT l.nomeAutor FROM Livro l WHERE (l.anoNascimento <= :ano AND ((l.anoMorte IS NULL) OR (l.anoMorte >= :ano)))")
    List<String> listAutorsAliveInEspecificYear(@Param("ano") Integer ano);

    @Query("SELECT l.linguagens FROM Livro l")
    List<String> listLanguage();

    @Query("SELECT l.titulo FROM Livro l WHERE :language = l.linguagens")
    List<String> listBooksInEspecificLanguage(@Param("language") String language);
}
