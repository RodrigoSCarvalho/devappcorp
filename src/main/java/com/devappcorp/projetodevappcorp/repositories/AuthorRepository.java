package com.devappcorp.projetodevappcorp.repositories;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAllBySobrenomeOrderByNome(String sobrenome);

    @Query("SELECT a.recursos FROM Author a WHERE a.id = :id")
    List<Recurso> findAuthorRecursos(@Param("id") Long id);

    // Bulk Remove
    @Query( value = "SELECT ra.recurso_id FROM recurso_autores ra JOIN Recurso r ON ra.recurso_id = r.id JOIN recurso_autores ra2 ON r.id = ra2.recurso_id WHERE ra2.author_id = :id " +
            "GROUP BY ra.recurso_id HAVING count(ra.author_id) = 1", nativeQuery = true)
    List<Integer> findRecursoIds(@Param("id") Long id);

    @Query( value = "SELECT * FROM author WHERE id NOT IN (SELECT a.id FROM author a, recurso_autores ra WHERE ra.author_id = a.id AND ra.recurso_id = :id);", nativeQuery = true)
    List<Author> findAutoresDoRecurso(@Param("id") Long id);

    Optional<Author> findById(Long id);

}