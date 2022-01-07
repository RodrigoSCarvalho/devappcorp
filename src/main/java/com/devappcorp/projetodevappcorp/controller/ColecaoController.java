package com.devappcorp.projetodevappcorp.controller;


import com.devappcorp.projetodevappcorp.entities.Colecao;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.services.ColecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ColecaoController {

    @Autowired
    private ColecaoService colecaoService;

    @GetMapping("/colecao")
    public @ResponseBody Iterable<Colecao> getAllColecao(){
        return this.colecaoService.getAllColecao();
    }

    @GetMapping("/colecao/{id}/recursos")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Recurso> findColecaoRecusos(@PathVariable Long id){
        return this.colecaoService.findColecaoRecusos(id);

    }

    @PostMapping("/colecao")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewColecao(@RequestBody Colecao colecao){
        return this.colecaoService.addNewColecao(colecao);
    }

    @PostMapping("/recurso/{recursoId}/colecao")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addNewColecaoRecurso(@RequestBody Colecao colecao, @PathVariable Long recursoId) {
        this.colecaoService.addNewColecaoRecurso(colecao, recursoId);
        return new ResponseEntity(colecao, HttpStatus.CREATED);
    }

    @PutMapping("/colecao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateColecao(@PathVariable Long id, @RequestBody Colecao colecao){
        this.colecaoService.updateColecao(id, colecao);
        return new ResponseEntity(colecao, HttpStatus.OK);
    }

    @PutMapping("/recurso/{recursoId}/colecao/{colecaoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateColecaoRecurso(@PathVariable Long recursoId, @PathVariable Long colecaoId, @RequestBody Colecao colecao){
        this.colecaoService.updateColecaoRecurso(recursoId, colecaoId, colecao);
        return new ResponseEntity(colecao, HttpStatus.OK);
    }

    @DeleteMapping("/colecao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteColecao(@PathVariable Long id){
        this.colecaoService.deleteColecao(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("recurso/{recursoId}/colecao/{colecaoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteColecaoRecurso(@PathVariable Long recursoId, @PathVariable Long colecaoId){
        this.colecaoService.deleteColecaoRecurso(recursoId, colecaoId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
