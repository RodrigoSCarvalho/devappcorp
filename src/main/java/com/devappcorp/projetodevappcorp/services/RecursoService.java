package com.devappcorp.projetodevappcorp.services;

import com.devappcorp.projetodevappcorp.entities.*;

import java.util.List;
import java.util.Optional;


public interface RecursoService {
    void addNewRecurso (Recurso recurso, Long authorId);

    void updateRecurso(Long id, Recurso recurso);

    void updateAutorRecurso(Long authorId, Long recursoId, Recurso recurso);


    void deleteRecurso(Long id);

    List<Recurso> getAllRecurso();

    List<Recurso> findTop5Recursos();

    List<Recurso> getAllRecursoPeloTitulo(String titulo);

    Optional<Recurso> findRecursoById(Long id);

    List<String> findPalavrasChaveById(Long id);

    List<Author> findAutoresDoRecurso(Long id);

    void disassociateRecurso(Long colecaoId, Long recursoId);

    void associarRecurso(Long colecaoId, Long recursoId);

    List<Integer> findCursoLivre();

    List<Integer> findEventoLivre();


}
