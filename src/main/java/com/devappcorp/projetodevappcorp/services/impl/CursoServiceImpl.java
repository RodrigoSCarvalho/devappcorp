package com.devappcorp.projetodevappcorp.services.impl;

import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.repositories.CursoRepository;
import com.devappcorp.projetodevappcorp.repositories.RecursoRepository;
import com.devappcorp.projetodevappcorp.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    RecursoRepository recursoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Override
    public void addNewCurso(Curso curso, Long recursoId) {
        recursoRepository.findById(recursoId).map(recurso -> {
            recurso.setColecao(curso);
            curso.getRecursos().add(recurso);
            cursoRepository.save(curso);
            return recursoRepository.save(recurso);
        });
    }

    @Override
    public void addCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    @Override
    public void updateCurso(Long id, Curso curso) {
        cursoRepository.findById(id).map(cursoExistente -> {
            cursoExistente.setTitulo(curso.getTitulo());
            cursoExistente.setImagem(curso.getImagem());
            cursoExistente.setDescricao(curso.getDescricao());
            cursoExistente.setData_registro(curso.getData_registro());

            return cursoRepository.save(cursoExistente);
        });
    }

    @Override
    public void updateCursoRecurso(Long recursoId, Long cursoId, Curso curso) {
        recursoRepository.findById(recursoId).map(recurso -> {
            cursoRepository.findById(cursoId).map(cursoExistente -> {
                recurso.setColecao(cursoExistente);
                cursoExistente.getRecursos().add(recurso);
                cursoExistente.setDescricao(curso.getDescricao());
                cursoExistente.setTitulo(curso.getTitulo());
                cursoExistente.setImagem(curso.getImagem());
                cursoExistente.setData_registro(curso.getData_registro());


                cursoRepository.save(cursoExistente);

                return recursoRepository.save(recurso);
            });


            return curso;
        });
    }

    @Override
    public void deleteCurso(Long id) {
        cursoRepository.findById(id).map(curso -> {
            cursoRepository.delete(curso);
            return curso;
        });
    }

    @Override
    public void deleteCursoRecurso(Long recursoId, Long cursoId) {
        cursoRepository.findById(cursoId).map(curso -> {
            recursoRepository.findById(recursoId).map(recurso -> {
                curso.getRecursos().remove(recurso);
                recurso.setColecao(null);


                return recursoRepository.save(recurso);
            });
            if (curso.getRecursos().size() >= 1) return curso;
            cursoRepository.delete(curso);
            return curso;
        });
    }

    @Override
    public List<Recurso> findCursoRecursos(Long id) {
        return cursoRepository.findCursoRecursos(id);
    }


    @Override
    public List<Curso> getAllCurso() {
        return cursoRepository.findAll();
    }


}
