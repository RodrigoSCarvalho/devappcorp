package com.devappcorp.projetodevappcorp.repositories;

import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Evento;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT e.recursos FROM Evento e WHERE e.id = :id")
    List<Recurso> findEventoRecursos(@Param("id") Long id);

    @Query("SELECT e FROM Evento e WHERE e.data_criacao >= :data_criacao and e.data_fim <= :data_fim")
    List<Evento> findEventoByDatas(@Param("data_criacao") String data_criacao, @Param("data_fim") String data_fim);

    List<Evento> findTop5ByOrderByIdDesc();

    Optional<Evento> findById(Long id);

    @Query(value = "SELECT evento_id FROM evento WHERE evento_id NOT IN (SELECT e.evento_id FROM evento e, recurso r WHERE e.evento_id = r.colecao_id)", nativeQuery = true)
    List<Integer> findEventoLivre();
}