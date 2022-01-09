package com.devappcorp.projetodevappcorp.controller;

import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.services.RecursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Recuperar todos recursos")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
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


    @Operation(summary = "Salvar um recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Recurso cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PostMapping("/author/{authorId}/recurso")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addNewRecurso(@PathVariable(value= "authorId") Long authorId, @RequestBody Recurso recurso){
        this.recursoService.addNewRecurso(recurso, authorId);
        return new ResponseEntity<> (recurso, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Recurso atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("/recurso/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity updateRecurso(@RequestBody Recurso recurso, @PathVariable Long id){
        this.recursoService.updateRecurso(id, recurso);
        return new ResponseEntity(recurso, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar um recurso associando um autor")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Recurso atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("/author/{authorId}/recurso/{recursoId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity updateAutorRecurso(@RequestBody Recurso recurso, @PathVariable Long authorId, @PathVariable Long recursoId){
        this.recursoService.updateAutorRecurso(authorId,  recursoId, recurso);
        return new ResponseEntity(recurso, HttpStatus.OK);
    }

    @Operation(summary = "Deletar um recurso desassociando o autor")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Recurso dessassociado/deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @DeleteMapping("/author/{authorId}/recurso/{recursoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteRecurso(@PathVariable Long recursoId, @PathVariable Long authorId ){
        this.recursoService.deleteRecurso(authorId, recursoId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
