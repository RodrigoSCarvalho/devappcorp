package com.devappcorp.projetodevappcorp.controller;

import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping("/curso")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Curso> getAllCurso(){
        return this.cursoService.getAllCurso();
    }

    @PostMapping("/curso")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addCurso(@RequestBody Curso curso){
        this.cursoService.addCurso(curso);
        return new ResponseEntity<>(curso, HttpStatus.CREATED);
    }

    @PostMapping("/recurso/{recursoId}/curso")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addNewCurso(@RequestBody Curso curso, @PathVariable Long recursoId){
        this.cursoService.addNewCurso(curso, recursoId);
        return new ResponseEntity<>(curso, HttpStatus.CREATED);
    }

    @PutMapping("/curso/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateCurso(@RequestBody Curso curso, @PathVariable Long id){
        this.cursoService.updateCurso(id, curso);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @PutMapping("/recurso/{recursoId}/curso/{cursoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateCursoRecurso(@RequestBody Curso curso, @PathVariable Long recursoId, @PathVariable Long cursoId){
        this.cursoService.updateCursoRecurso(recursoId, cursoId, curso);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @DeleteMapping("/curso/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteCurso(@PathVariable Long id){
        this.cursoService.deleteCurso(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/recurso/{recursoId}/curso/{cursoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteCursoRecurso(@PathVariable Long recursoId, @PathVariable Long cursoId){
        this.cursoService.deleteCursoRecurso(recursoId, cursoId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/curso/{id}/recursos")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Recurso> findCursoRecusos(@PathVariable Long id){
        return this.cursoService.findCursoRecursos(id);

    }
}
