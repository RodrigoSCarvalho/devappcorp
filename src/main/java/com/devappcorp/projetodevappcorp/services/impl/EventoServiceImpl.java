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
    public Evento addEvento(Evento evento) {
        if (evento.getData_fim() != null && evento.getData_criacao() != null) {
            String fim = evento.getData_fim();
            String criacao = evento.getData_criacao();

            fim = fim.replace("-", "");
            criacao = criacao.replace("-", "");
            if (Integer.parseInt(fim) < Integer.parseInt(criacao)) {
                throw new Error();
            }
        }
        if (evento.getData_criacao() != null) {
            if (evento.getData_criacao().length() != 8 && evento.getData_criacao().length() != 10) {
                throw new Error();
            }
        }


        if (evento.getData_fim() != null) {
            if (evento.getData_fim().length() != 8 && evento.getData_fim().length() != 10) {
                throw new Error();
            }
        }

        eventoRepository.save(evento);
        return evento;


    }

    @Override
    public void updateEvento(Long id, Evento evento) {
        eventoRepository.findById(id).map(eventoExistente -> {
            if (evento.getTitulo() != null)
                eventoExistente.setTitulo(evento.getTitulo());
            if (evento.getImagem() != null)
                eventoExistente.setImagem(evento.getImagem());
            if (evento.getDescricao() != null)
                eventoExistente.setDescricao(evento.getDescricao());
            if (evento.getData_criacao() != null)
                if (evento.getData_fim().length() != 8 && evento.getData_fim().length() != 10) {
                    throw new Error();
                } else
                    eventoExistente.setData_fim(evento.getData_fim());
            if (evento.getData_fim() != null)
                if (evento.getData_fim().length() != 8 && evento.getData_fim().length() != 10) {
                    throw new Error();
                } else
                    eventoExistente.setData_criacao(evento.getData_criacao());

            return eventoRepository.save(eventoExistente);
        });
    }

    @Override
    public void updateEventoRecurso(Long recursoId, Long eventoId, Evento evento) {
        recursoRepository.findById(recursoId).map(recurso -> {
            eventoRepository.findById(eventoId).map(eventoExistente -> {
                recurso.setColecao(eventoExistente);
                if (!evento.getRecursos().isEmpty())
                    eventoExistente.getRecursos().add(recurso);
                if (evento.getTitulo() != null)
                    eventoExistente.setTitulo(evento.getTitulo());
                if (evento.getImagem() != null)
                    eventoExistente.setImagem(evento.getImagem());
                if (evento.getDescricao() != null)
                    eventoExistente.setDescricao(evento.getDescricao());
                if (evento.getData_criacao() != null)
                    if (evento.getData_fim().length() != 8 && evento.getData_fim().length() != 10) {
                        throw new Error();
                    } else
                        eventoExistente.setData_fim(evento.getData_fim());
                if (evento.getData_fim() != null)
                    if (evento.getData_fim().length() != 8 && evento.getData_fim().length() != 10) {
                        throw new Error();
                    } else
                        eventoExistente.setData_criacao(evento.getData_criacao());

                eventoRepository.save(eventoExistente);

                return recursoRepository.save(recurso);
            });


            return evento;
        });
    }


    @Override
    public void deleteEvento(Long id) {
        eventoRepository.findById(id).map(evento -> {
            for (Recurso recurso : evento.getRecursos()) {
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
