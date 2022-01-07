package com.devappcorp.projetodevappcorp.services;

import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Recurso;

import java.util.List;

public interface CursoService {

    void addNewCurso (Curso curso, Long recursoId);

    void addCurso(Curso curso);

    void updateCurso(Long id, Curso curso);

    void deleteCurso(Long id);

    void updateCursoRecurso(Long recursoId, Long cursoId, Curso curso);

    void deleteCursoRecurso(Long recursoId, Long cursoId);

    List<Recurso> findCursoRecursos(Long id);

    List<Curso> getAllCurso();
}
