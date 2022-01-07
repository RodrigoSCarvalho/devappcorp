package com.devappcorp.projetodevappcorp.repositories;

import com.devappcorp.projetodevappcorp.entities.Colecao;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColecaoRepository extends JpaRepository<Colecao, Long> {

    @Query("SELECT c.recursos FROM Colecao c WHERE c.id = :id")
    List<Recurso> findColecaoRecusos(Long id);
}