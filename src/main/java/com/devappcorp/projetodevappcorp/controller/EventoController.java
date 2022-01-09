package com.devappcorp.projetodevappcorp.controller;

import com.devappcorp.projetodevappcorp.entities.Evento;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.services.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Operation(summary = "Recuperar todos eventos")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/evento")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Iterable<Evento> getAllEvento(){
        return this.eventoService.getAllEvento();
    }


    @Operation(summary = "Salvar um evento e associar a um recurso existente")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Evento cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PostMapping("/recurso/{recursoId}/evento")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addNewEvento(@RequestBody Evento evento, @PathVariable Long recursoId){
        this.eventoService.addNewEvento(evento, recursoId);
        return new ResponseEntity(evento, HttpStatus.CREATED);
    }

    @Operation(summary = "Salvar um evento")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Evento cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PostMapping("/evento")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addEvento(@RequestBody Evento evento) {
        this.eventoService.addEvento(evento);
        return new ResponseEntity(evento, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um evento")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("/evento/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateEvento(@PathVariable Long id, @RequestBody Evento evento){
        this.eventoService.updateEvento(id, evento);
        return new ResponseEntity(evento, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar um evento e associar a um recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("/recurso/{recursoId}/evento/{eventoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateEventoRecurso(@PathVariable Long recursoId, @PathVariable Long eventoId, @RequestBody Evento evento){
        this.eventoService.updateEventoRecurso(recursoId, eventoId, evento);
        return new ResponseEntity(evento, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar um curso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @DeleteMapping("/evento/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteEvento(@PathVariable Long id){
        this.eventoService.deleteEvento(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Deletar um evento e desassociar do recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Evento deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @DeleteMapping("recurso/{recursoId}/evento/{eventoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteEventoRecurso(@PathVariable Long recursoId, @PathVariable Long eventoId){
        this.eventoService.deleteEventoRecurso(recursoId, eventoId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Recuperar todos recursos de um dado evento")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/evento/{id}/recursos")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Recurso> findEventoRecusos(@PathVariable Long id){
        return this.eventoService.findEventoRecursos(id);

    }

    @Operation(summary = "Recuperar todos eventos dentro de um dado período de tempo")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/evento/{data_criacao}/{data_fim}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Evento> findEventoRecusos(@PathVariable String data_criacao, @PathVariable String data_fim){
        return this.eventoService.findEventoByDatas(data_criacao, data_fim);

    }
}
