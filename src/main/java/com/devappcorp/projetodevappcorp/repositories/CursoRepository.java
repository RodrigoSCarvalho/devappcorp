package com.devappcorp.projetodevappcorp.repositories;

import com.devappcorp.projetodevappcorp.entities.Colecao;
import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    interface ColecaoRepository extends JpaRepository<Colecao, Long> {
    }

    @Query("SELECT c.recursos FROM Curso c WHERE c.id = :id")
    List<Recurso> findCursoRecursos(@Param("id") Long id);

    List<Curso> findTop5ByOrderByIdDesc();

    Optional<Curso> findById(Long id);

    @Query(value = "SELECT c.curso_id FROM curso c WHERE c.curso_id NOT IN ( SELECT c.curso_id FROM curso c, recurso r WHERE c.curso_id = r.colecao_id)", nativeQuery = true)
    List<Integer> findCursoLivre();
}