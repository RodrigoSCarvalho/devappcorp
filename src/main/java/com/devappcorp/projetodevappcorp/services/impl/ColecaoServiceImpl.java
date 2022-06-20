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
    public void addNewColecao(Colecao colecao) {


        colecaoRepository.save(colecao);;

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
                if(!colecao.getRecursos().isEmpty())
                    colecaoExistente.getRecursos().add(recurso);
                if(colecao.getTitulo() != null)
                    colecaoExistente.setTitulo(colecao.getTitulo());
                if(colecao.getImagem() != null)
                    colecaoExistente.setImagem(colecao.getImagem());
                if(colecao.getDescricao() != null)
                    colecaoExistente.setDescricao(colecao.getDescricao());


                colecaoRepository.save(colecaoExistente);

                return recursoRepository.save(recurso);

            });


            return colecao;
        });
    }

    @Override
    public void updateColecao(Long id, Colecao colecao) {
        colecaoRepository.findById(id).map(colecaoExistente -> {
            if(colecao.getTitulo() != null)
                colecaoExistente.setTitulo(colecao.getTitulo());
            if(colecao.getImagem() != null)
                colecaoExistente.setImagem(colecao.getImagem());
            if(colecao.getDescricao() != null)
                colecaoExistente.setDescricao(colecao.getDescricao());

            return colecaoRepository.save(colecaoExistente);
        });
    }

    @Override
    public void deleteColecao(Long id) {
        colecaoRepository.findById(id).map(colecao -> {
            for (Recurso recurso : colecao.getRecursos()){
                recurso.setColecao(null);
                recursoRepository.save(recurso);
            }

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
