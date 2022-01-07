package com.devappcorp.projetodevappcorp.services;


import com.devappcorp.projetodevappcorp.entities.Colecao;
import com.devappcorp.projetodevappcorp.entities.Evento;
import com.devappcorp.projetodevappcorp.entities.Recurso;

import java.util.List;

public interface EventoService {

    void addNewEvento (Evento evento, Long recursoId);

    void addEvento(Evento evento);

    void updateEvento(Long id, Evento evento);

    void deleteEvento(Long id);

    void updateEventoRecurso(Long recursoId, Long eventoId, Evento evento);

    void deleteEventoRecurso(Long recursoId, Long eventoId);

    List<Recurso> findEventoRecursos(Long id);

    List<Evento> findEventoByDatas(String data_criacao, String data_fim);

    List<Evento> getAllEvento();
}
