package com.devappcorp.projetodevappcorp.controller;

import com.devappcorp.projetodevappcorp.entities.Evento;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/evento")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Iterable<Evento> getAllEvento(){
        return this.eventoService.getAllEvento();
    }

    @GetMapping("/recurso/{recursoId}/evento")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Evento> getAllEventoRecursos(){
        return this.eventoService.getAllEvento();
    }

    @PostMapping("/recurso/{recursoId}/evento")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addNewEvento(@RequestBody Evento evento, @PathVariable Long recursoId){
        this.eventoService.addNewEvento(evento, recursoId);
        return new ResponseEntity(evento, HttpStatus.CREATED);
    }

    @PostMapping("/evento")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addEvento(@RequestBody Evento evento) {
        this.eventoService.addEvento(evento);
        return new ResponseEntity(evento, HttpStatus.CREATED);
    }

    @PutMapping("/evento/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateEvento(@PathVariable Long id, @RequestBody Evento evento){
        this.eventoService.updateEvento(id, evento);
        return new ResponseEntity(evento, HttpStatus.OK);
    }

    @PutMapping("/recurso/{recursoId}/evento/{eventoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateEventoRecurso(@PathVariable Long recursoId, @PathVariable Long eventoId, @RequestBody Evento evento){
        this.eventoService.updateEventoRecurso(recursoId, eventoId, evento);
        return new ResponseEntity(evento, HttpStatus.OK);
    }

    @DeleteMapping("/evento/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteEvento(@PathVariable Long id){
        this.eventoService.deleteEvento(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("recurso/{recursoId}/evento/{eventoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteEventoRecurso(@PathVariable Long recursoId, @PathVariable Long eventoId){
        this.eventoService.deleteEventoRecurso(recursoId, eventoId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/evento/{id}/recursos")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Recurso> findEventoRecusos(@PathVariable Long id){
        return this.eventoService.findEventoRecursos(id);

    }


    @GetMapping("/evento/{data_criacao}/{data_fim}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Evento> findEventoRecusos(@PathVariable String data_criacao, @PathVariable String data_fim){
        return this.eventoService.findEventoByDatas(data_criacao, data_fim);

    }
}
