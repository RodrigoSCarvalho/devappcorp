package com.devappcorp.projetodevappcorp.services.impl;

import com.devappcorp.projetodevappcorp.entities.Evento;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.repositories.EventoRepository;
import com.devappcorp.projetodevappcorp.repositories.RecursoRepository;
import com.devappcorp.projetodevappcorp.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    RecursoRepository recursoRepository;

    @Override
    public void addNewEvento(Evento evento, Long recursoId) {
        recursoRepository.findById(recursoId).map(recurso -> {
            recurso.setColecao(evento);
            evento.getRecursos().add(recurso);
            eventoRepository.save(evento);
            return recursoRepository.save(recurso);
        });
    }

    @Override
    public void addEvento(Evento evento) {
        eventoRepository.save(evento);
    }

    @Override
    public void updateEvento(Long id, Evento evento) {
        eventoRepository.findById(id).map(eventoExistente -> {
            eventoExistente.setTitulo(evento.getTitulo());
            eventoExistente.setImagem(evento.getImagem());
            eventoExistente.setDescricao(evento.getDescricao());
            eventoExistente.setData_fim(evento.getData_fim());
            eventoExistente.setData_criacao(evento.getData_criacao());

            return eventoRepository.save(eventoExistente);
        });
    }

    @Override
    public void updateEventoRecurso(Long recursoId, Long eventoId, Evento evento) {
        recursoRepository.findById(recursoId).map(recurso -> {
            eventoRepository.findById(eventoId).map(evetoExistente -> {
                recurso.setColecao(evetoExistente);
                evetoExistente.getRecursos().add(recurso);
                evetoExistente.setDescricao(evento.getDescricao());
                evetoExistente.setTitulo(evento.getTitulo());
                evetoExistente.setImagem(evento.getImagem());
                evetoExistente.setData_criacao(evento.getData_criacao());
                evetoExistente.setData_fim(evento.getData_fim());

                eventoRepository.save(evetoExistente);

                return recursoRepository.save(recurso);
            });


            return evento;
        });
    }


    @Override
    public void deleteEvento(Long id) {
        eventoRepository.findById(id).map(evento -> {
            for (Recurso recurso : evento.getRecursos()){
                recurso.setColecao(null);
                recursoRepository.save(recurso);
            }
            eventoRepository.delete(evento);
            return evento;
        });
    }

    @Override
    public List<Recurso> findEventoRecursos(Long id) {
        return eventoRepository.findEventoRecursos(id);
    }

    @Override
    public List<Evento> findEventoByDatas(String data_criacao, String data_fim) {
        return eventoRepository.findEventoByDatas(data_criacao, data_fim);
    }

    @Override
    public List<Evento> findTop5Recursos() {
        return eventoRepository.findTop5ByOrderByIdDesc();
    }

    @Override
    public List<Evento> getAllEvento() {
        return eventoRepository.findAll();
    }

    @Override
    public List<Recurso> findRecursoSemColecao() {
        return recursoRepository.findRecursoSemColecao();
    }

    @Override
    public Optional<Evento> findEventoById(Long id) {
        return eventoRepository.findById(id);
    }
}
