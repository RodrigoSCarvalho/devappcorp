package com.devappcorp.projetodevappcorp.repositories;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAllBySobrenomeOrderByNome(String sobrenome);

    @Query("SELECT a.recursos FROM Author a WHERE a.id = :id")
    List<Recurso> findAuthorRecursos(@Param("id") Long id);
}