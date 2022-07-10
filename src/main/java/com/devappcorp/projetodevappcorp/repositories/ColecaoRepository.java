package com.devappcorp.projetodevappcorp.repositories;

import com.devappcorp.projetodevappcorp.entities.Colecao;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/** ColecaoRepository interface. */
public interface ColecaoRepository extends JpaRepository<Colecao, Long> {

  @Query("SELECT c.recursos FROM Colecao c WHERE c.id = :id")
  List<Recurso> findColecaoRecusos(Long id);


}