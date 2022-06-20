package com.devappcorp.projetodevappcorp.controller;


import com.devappcorp.projetodevappcorp.entities.Colecao;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.services.ColecaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ColecaoController {

    @Autowired
    private ColecaoService colecaoService;

    @Operation(summary = "Recuperar todas coleções")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/colecao")
    public @ResponseBody Iterable<Colecao> getAllColecao(){
        return this.colecaoService.getAllColecao();
    }

    @Operation(summary = "Recuperar todos recursos de uma determinada colecao")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/colecao/{id}/recursos")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Recurso> findColecaoRecusos(@PathVariable Long id){
        return this.colecaoService.findColecaoRecusos(id);

    }

    @Operation(summary = "Salvar uma colecao")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Colecao cadastrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PostMapping("/colecao")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addNewColecao(@RequestBody Colecao colecao){
        this.colecaoService.addNewColecao(colecao);
        return new ResponseEntity<>(colecao, HttpStatus.CREATED);
    }

    @Operation(summary = "Salvar uma colecao associada a um recurso existente")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Colecao cadastrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PostMapping("/recurso/{recursoId}/colecao")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addNewColecaoRecurso(@RequestBody Colecao colecao, @PathVariable Long recursoId) {
        this.colecaoService.addNewColecaoRecurso(colecao, recursoId);
        return new ResponseEntity(colecao, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar uma colecao")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Colecao atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("/colecao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateColecao(@PathVariable Long id, @RequestBody Colecao colecao){
        this.colecaoService.updateColecao(id, colecao);
        return new ResponseEntity(colecao, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar uma colecao e associar a um recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Colecao atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("/recurso/{recursoId}/colecao/{colecaoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateColecaoRecurso(@PathVariable Long recursoId, @PathVariable Long colecaoId, @RequestBody Colecao colecao){
        this.colecaoService.updateColecaoRecurso(recursoId, colecaoId, colecao);
        return new ResponseEntity(colecao, HttpStatus.OK);
    }

    @Operation(summary = "Deletar uma colecao")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Colecao deletada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @DeleteMapping("/colecao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteColecao(@PathVariable Long id){
        this.colecaoService.deleteColecao(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
