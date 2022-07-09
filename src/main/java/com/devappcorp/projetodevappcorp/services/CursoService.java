package com.devappcorp.projetodevappcorp.services;

import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Recurso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    void addNewCurso (Curso curso, Long recursoId);

    Curso addCurso(Curso curso);

    void updateCurso(Long id, Curso curso);

    void deleteCurso(Long id);

    void updateCursoRecurso(Long recursoId, Long cursoId, Curso curso);

    List<Recurso> findCursoRecursos(Long id);

    List<Curso> findTop5Recursos();

    List<Curso> getAllCurso();

    List<Recurso> findRecursoSemColecao();

    Optional<Curso> findCursoById(Long id);
}

