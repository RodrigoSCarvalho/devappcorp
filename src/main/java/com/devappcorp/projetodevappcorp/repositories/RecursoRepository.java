package com.devappcorp.projetodevappcorp.repositories;

import com.devappcorp.projetodevappcorp.entities.Colecao;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {
    List<Recurso> findAllByTitulo(String titulo);

    List<Recurso> findTop5ByOrderByIdDesc();

    Optional<Recurso> findById(Long id);

    @Query( value = "SELECT palavras_chave FROM recurso_palavras_chave WHERE recurso_id = :id", nativeQuery = true)
    List<String> findPalavrasChaveById(@Param("id") Long id);

    @Query( value = "SELECT * FROM recurso WHERE colecao_id is null;", nativeQuery = true)
    List<Recurso> findRecursoSemColecao();

    @Modifying(clearAutomatically = true)
    @Query ( value = "UPDATE Recurso SET colecao_id = NULL WHERE colecao_id = :colecaoId AND id = :recursoId", nativeQuery = true)
    void disassociateRecurso(@Param("colecaoId") Long colecaoId , @Param("recursoId") Long recursoId );

        @Modifying(clearAutomatically = true)
    @Query ( value = "UPDATE Recurso SET colecao_id = :colecaoId WHERE id = :recursoId", nativeQuery = true)
    void associarRecurso(@Param("colecaoId") Long colecaoId , @Param("recursoId") Long recursoId );



}