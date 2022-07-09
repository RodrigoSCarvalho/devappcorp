package com.devappcorp.projetodevappcorp.services;


import com.devappcorp.projetodevappcorp.entities.Evento;
import com.devappcorp.projetodevappcorp.entities.Recurso;

import java.util.List;
import java.util.Optional;

public interface EventoService {

    void addNewEvento (Evento evento, Long recursoId);

    Evento addEvento(Evento evento);

    void updateEvento(Long id, Evento evento);

    void deleteEvento(Long id);

    void updateEventoRecurso(Long recursoId, Long eventoId, Evento evento);

    List<Recurso> findEventoRecursos(Long id);

    List<Evento> findEventoByDatas(String data_criacao, String data_fim);

    List<Evento> findTop5Recursos();

    List<Evento> getAllEvento();

    List<Recurso> findRecursoSemColecao();

    Optional<Evento> findEventoById(Long id);
}
