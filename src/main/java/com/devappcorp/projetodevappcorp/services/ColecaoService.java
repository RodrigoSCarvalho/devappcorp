package com.devappcorp.projetodevappcorp.services;

import com.devappcorp.projetodevappcorp.entities.Colecao;
import com.devappcorp.projetodevappcorp.entities.Recurso;

import java.util.List;

public interface ColecaoService {

    String addNewColecao(Colecao colecao);

    void addNewColecaoRecurso(Colecao colecao, Long recursoId);

    void updateColecao(Long id, Colecao colecao);

    void deleteColecao(Long id);

    void updateColecaoRecurso(Long recursoId, Long colecaoId, Colecao colecao);

    void deleteColecaoRecurso(Long recursoId, Long colecaoId);

    Iterable<Colecao> getAllColecao();

    List<Colecao> getAllColecaoPeloTitulo(String titulo);

    List<Recurso> findColecaoRecusos(Long id);

}
