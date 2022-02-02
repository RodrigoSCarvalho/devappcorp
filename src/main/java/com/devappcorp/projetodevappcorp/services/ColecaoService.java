package com.devappcorp.projetodevappcorp.services;

import com.devappcorp.projetodevappcorp.entities.Colecao;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColecaoService {

    String addNewColecao(Colecao colecao);

    void addNewColecaoRecurso(Colecao colecao, Long recursoId);

    void updateColecao(Long id, Colecao colecao);

    void deleteColecao(Long id);

    void updateColecaoRecurso(Long recursoId, Long colecaoId, Colecao colecao);

    Iterable<Colecao> getAllColecao();

    List<Colecao> getAllColecaoPeloTitulo(String titulo);

    List<Recurso> findColecaoRecusos(Long id);
}
