package com.devappcorp.projetodevappcorp.repositories;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {
    List<Recurso> findAllByTitulo(String titulo);

    List<Recurso> findTop5ByOrderByIdDesc();

    Optional<Recurso> findById(Long id);

    @Query( value = "SELECT palavras_chave FROM recurso_palavras_chave WHERE recurso_id = :id", nativeQuery = true)
    List<String> findPalavrasChaveById(@Param("id") Long id);

    @Query( value = "SELECT * FROM recurso WHERE colecao_id is null;", nativeQuery = true)
    List<Recurso> findRecursoSemColecao();

}