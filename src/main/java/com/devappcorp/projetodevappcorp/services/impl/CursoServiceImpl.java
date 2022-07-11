package com.devappcorp.projetodevappcorp.services.impl;

import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.repositories.CursoRepository;
import com.devappcorp.projetodevappcorp.repositories.RecursoRepository;
import com.devappcorp.projetodevappcorp.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Curso addCurso(Curso curso) {

        if (curso.getData_registro() != null) {
            if (curso.getData_registro().length() != 8 && curso.getData_registro().length() != 10) {
                throw new Error();
            }
        }

        cursoRepository.save(curso);
        return curso;
    }

    @Override
    public void updateCurso(Long id, Curso curso) {
        cursoRepository.findById(id).map(cursoExistente -> {
            if (curso.getTitulo() != null)
                cursoExistente.setTitulo(curso.getTitulo());
            if (curso.getImagem() != null)
                cursoExistente.setImagem(curso.getImagem());
            if (curso.getDescricao() != null)
                cursoExistente.setDescricao(curso.getDescricao());
            if (curso.getData_registro() != null)
                if (curso.getData_registro().length() != 8 && curso.getData_registro().length() != 10) {
                    throw new Error();
                } else
                    cursoExistente.setData_registro(curso.getData_registro());

            return cursoRepository.save(cursoExistente);
        });
    }

    @Override
    public void updateCursoRecurso(Long recursoId, Long cursoId, Curso curso) {
        recursoRepository.findById(recursoId).map(recurso -> {
            cursoRepository.findById(cursoId).map(cursoExistente -> {
                if (!curso.getRecursos().isEmpty())
                    recurso.setColecao(cursoExistente);
                if (curso.getTitulo() != null)
                    cursoExistente.setTitulo(curso.getTitulo());
                if (curso.getImagem() != null)
                    cursoExistente.setImagem(curso.getImagem());
                if (curso.getDescricao() != null)
                    cursoExistente.setDescricao(curso.getDescricao());
                if (curso.getData_registro() != null)
                    if (curso.getData_registro().length() != 8 && curso.getData_registro().length() != 10) {
                        throw new Error();
                    } else
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
            for (Recurso recurso : curso.getRecursos()) {
                recurso.setColecao(null);
                recursoRepository.save(recurso);
            }
            cursoRepository.delete(curso);
            return curso;
        });
    }

    @Override
    public List<Recurso> findCursoRecursos(Long id) {
        return cursoRepository.findCursoRecursos(id);
    }

    @Override
    public List<Curso> findTop5Recursos() {
        return cursoRepository.findTop5ByOrderByIdDesc();
    }


    @Override
    public List<Curso> getAllCurso() {
        return cursoRepository.findAll();
    }

    @Override
    public List<Recurso> findRecursoSemColecao() {
        return recursoRepository.findRecursoSemColecao();
    }

    @Override
    public Optional<Curso> findCursoById(Long id) {
        return cursoRepository.findById(id);
    }


}
