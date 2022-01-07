package com.devappcorp.projetodevappcorp.controller;

import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.services.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RecursoController {
    @Autowired
    private RecursoService recursoService;

    @GetMapping("/recurso")
    public ResponseEntity getAllRecursos() {
        // This returns a JSON or XML with the users
        List<Recurso> recursos = recursoService.getAllRecurso();
        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }

    /*
    @GetMapping("{titulo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody List<Recurso> getAllRecursoPeloTitulo(@PathVariable String titulo){
        return this.recursoService.getAllRecursoPeloTitulo(titulo);
    }

     */


    @PostMapping("/author/{authorId}/recurso")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addNewRecurso(@PathVariable(value= "authorId") Long authorId, @RequestBody Recurso recurso){
        this.recursoService.addNewRecurso(recurso, authorId);
        return new ResponseEntity<> (recurso, HttpStatus.CREATED);
    }

    @PutMapping("/recurso/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity updateRecurso(@RequestBody Recurso recurso, @PathVariable Long id){
        this.recursoService.updateRecurso(id, recurso);
        return new ResponseEntity(recurso, HttpStatus.OK);
    }

    @PutMapping("/author/{authorId}/recurso/{recursoId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity updateAutorRecurso(@RequestBody Recurso recurso, @PathVariable Long authorId, @PathVariable Long recursoId){
        this.recursoService.updateAutorRecurso(authorId,  recursoId, recurso);
        return new ResponseEntity(recurso, HttpStatus.OK);
    }

    @DeleteMapping("/author/{authorId}/recurso/{recursoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteRecurso(@PathVariable Long recursoId, @PathVariable Long authorId ){
        this.recursoService.deleteRecurso(authorId, recursoId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
