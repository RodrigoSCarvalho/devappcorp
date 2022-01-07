package com.devappcorp.projetodevappcorp.services.impl;

import com.devappcorp.projetodevappcorp.entities.Colecao;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.repositories.ColecaoRepository;
import com.devappcorp.projetodevappcorp.repositories.RecursoRepository;
import com.devappcorp.projetodevappcorp.services.ColecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColecaoServiceImpl implements ColecaoService {
    @Autowired
    ColecaoRepository colecaoRepository;

    @Autowired
    RecursoRepository recursoRepository;

    @Override
    public String addNewColecao(Colecao colecao) {

        colecaoRepository.save(colecao);
        return "Salvo!";

    }

    @Override
    public void addNewColecaoRecurso(Colecao colecao, Long recursoId) {
        recursoRepository.findById(recursoId).map(recurso -> {
            recurso.setColecao(colecao);
            colecao.getRecursos().add(recurso);
            colecaoRepository.save(colecao);
            return recursoRepository.save(recurso);
        });

    }

    @Override
    public void updateColecaoRecurso(Long recursoId, Long colecaoId, Colecao colecao) {
        recursoRepository.findById(recursoId).map(recurso -> {
            colecaoRepository.findById(colecaoId).map(colecaoExistente -> {
                System.out.println("=====COLECAO EXISTENTE =====" + colecaoExistente);
                recurso.setColecao(colecaoExistente);
                colecaoExistente.getRecursos().add(recurso);
                colecaoExistente.setDescricao(colecao.getDescricao());
                colecaoExistente.setTitulo(colecao.getTitulo());
                colecaoExistente.setImagem(colecao.getImagem());


                colecaoRepository.save(colecaoExistente);

                return recursoRepository.save(recurso);

            });


            return colecao;
        });
    }

    @Override
    public void updateColecao(Long id, Colecao colecao) {
        colecaoRepository.findById(id).map(colecaoExistente -> {
            colecaoExistente.setTitulo(colecao.getTitulo());
            colecaoExistente.setImagem(colecao.getImagem());
            colecaoExistente.setDescricao(colecao.getDescricao());

            return colecaoRepository.save(colecaoExistente);
        });
    }

    @Override
    public void deleteColecao(Long id) {
        colecaoRepository.findById(id).map(colecao -> {

            colecaoRepository.delete(colecao);
            return colecao;
        });
    }



    @Override
    public void deleteColecaoRecurso(Long recursoId, Long colecaoId) {
        colecaoRepository.findById(colecaoId).map(colecao -> {
            recursoRepository.findById(recursoId).map(recurso -> {
                colecao.getRecursos().remove(recurso);
                recurso.setColecao(null);


                return recursoRepository.save(recurso);
            });
            colecaoRepository.delete(colecao);
            return colecao;
        });

    }

    @Override
    public Iterable<Colecao> getAllColecao() {
        return colecaoRepository.findAll();
    }

    @Override
    public List<Colecao> getAllColecaoPeloTitulo(String titulo) {
        return null;
    }

    @Override
    public List<Recurso> findColecaoRecusos(Long id) {
        return  colecaoRepository.findColecaoRecusos(id);
    }
}
