package com.devappcorp.projetodevappcorp.controller;

import com.devappcorp.projetodevappcorp.entities.*;
import com.devappcorp.projetodevappcorp.services.RecursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
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

    @Operation(summary = "Recuperar recurso pelo id")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/recurso/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Optional<Recurso> findRecursoById(@PathVariable Long id){
        return this.recursoService.findRecursoById(id);

    }

    @Operation(summary = "Recuperar palavras-chave pelo id do recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/recurso/{id}/palavraschave")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<String> findPalavrasChave(@PathVariable Long id){
        return this.recursoService.findPalavrasChaveById(id);

    }

    @Operation(summary = "Recuperar os 5 recursos recentes")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/recurso/recentes")
    public ResponseEntity getRecentesRecursos() {
        // This returns a JSON or XML with the users
        List<Recurso> recursos = recursoService.findTop5Recursos();
        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }

    @Operation(summary = "Recuperar os autores que não estejam associados ao recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/recurso/{id}/autores")
    public List<Author> findAutoresNotInRecurso(@PathVariable Long id) {
        // This returns a JSON or XML with the users
        return this.recursoService.findAutoresDoRecurso(id);
    }

    @Operation(summary = "Recuperar os cursos que não estejam associadas ao recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/recurso/cursos")
    public List<Integer> finCursosNotInRecurso() {
        // This returns a JSON or XML with the users
        return this.recursoService.findCursoLivre();
    }

    @Operation(summary = "Recuperar os eventos que não estejam associadas ao recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/recurso/eventos")
    public List<Integer> findEventosNotInRecurso() {
        // This returns a JSON or XML with the users
        return this.recursoService.findEventoLivre();
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
    public ResponseEntity addNewRecurso(@Valid @PathVariable(value= "authorId") Long authorId, @RequestBody Recurso recurso){
        this.recursoService.addNewRecurso(recurso, authorId);
        return new ResponseEntity(recurso, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Recurso atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("/recurso/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity updateRecurso(@Valid @RequestBody Recurso recurso, @PathVariable Long id){
        this.recursoService.updateRecurso(id, recurso);
        return new ResponseEntity(recurso, HttpStatus.OK);
    }

    @Operation(summary = "Desassociar um recurso de uma coleção")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Recurso atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/desassociar/{colecaoId}/recurso/{recursoId}")
    @ResponseStatus(HttpStatus.OK)
    public void dessassociarRecurso(@PathVariable Long colecaoId, @PathVariable Long recursoId){
        this.recursoService.disassociateRecurso(colecaoId, recursoId);
    }

    @Operation(summary = "Associar um recurso a uma coleção")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Recurso atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/associar/{colecaoId}/recurso/{recursoId}")
    @ResponseStatus(HttpStatus.OK)
    public void AssociarRecurso(@PathVariable Long colecaoId, @PathVariable Long recursoId){
        this.recursoService.associarRecurso(colecaoId, recursoId);
    }

    @Operation(summary = "Atualizar um recurso associando um autor")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Recurso atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("/author/{authorId}/recurso/{recursoId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity updateAutorRecurso(@Valid @RequestBody Recurso recurso, @PathVariable Long authorId, @PathVariable Long recursoId){
        this.recursoService.updateAutorRecurso(authorId,  recursoId, recurso);
        return new ResponseEntity(recurso, HttpStatus.OK);
    }

    @Operation(summary = "Deletar um recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Recurso dessassociado/deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @DeleteMapping("/recurso/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteRecurso(@PathVariable Long id ){
        this.recursoService.deleteRecurso(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
