package com.devappcorp.projetodevappcorp.services;

import com.devappcorp.projetodevappcorp.entities.Recurso;

import java.util.List;


public interface RecursoService {
    void addNewRecurso (Recurso recurso, Long authorId);

    void updateRecurso(Long id, Recurso recurso);

    void updateAutorRecurso(Long authorId, Long recursoId, Recurso recurso);

    void deleteRecursoAuthor(Long authorId, Long recursoId);

    void deleteRecurso(Long id);

    List<Recurso> getAllRecurso();

    List<Recurso> getAllRecursoPeloTitulo(String titulo);
}
