package com.devappcorp.projetodevappcorp.controller;

import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CursoController {

    @Autowired
    CursoService cursoService;

    @Operation(summary = "Recuperar todos cursos")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/curso")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Curso> getAllCurso(){
        return this.cursoService.getAllCurso();
    }

    @Operation(summary = "Recuperar cursos recentes")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/curso/recentes")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Curso> getCursoRecentes(){
        return this.cursoService.findTop5Recursos();
    }


    @Operation(summary = "Recuperar curso pelo id")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/curso/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Optional<Curso> findCursoById(@PathVariable Long id){
        return this.cursoService.findCursoById(id);

    }

    @Operation(summary = "Salvar um curso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Curso cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PostMapping("/curso")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addCurso(@Valid @RequestBody Curso curso){
        this.cursoService.addCurso(curso);
        return new ResponseEntity<>(curso, HttpStatus.CREATED);
    }

    @Operation(summary = "Salvar um curso associando a um recurso existente")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Curso cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PostMapping("/recurso/{recursoId}/curso")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addNewCurso(@Valid @RequestBody Curso curso, @PathVariable Long recursoId){
        this.cursoService.addNewCurso(curso, recursoId);
        return new ResponseEntity<>(curso, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um curso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("/curso/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateCurso(@Valid @RequestBody Curso curso, @PathVariable Long id){
        this.cursoService.updateCurso(id, curso);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar um curso e associar a um recurso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("/recurso/{recursoId}/curso/{cursoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateCursoRecurso(@Valid @RequestBody Curso curso, @PathVariable Long recursoId, @PathVariable Long cursoId){
        this.cursoService.updateCursoRecurso(recursoId, cursoId, curso);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @Operation(summary = "Deletar um curso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Curso deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @DeleteMapping("/curso/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteCurso(@PathVariable Long id){
        this.cursoService.deleteCurso(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Recuperar todos recursos de um curso")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/curso/{id}/recursos")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Recurso> findCursoRecusos(@PathVariable Long id){
        return this.cursoService.findCursoRecursos(id);

    }

    @Operation(summary = "Recuperar todos recursos não associados a uma coleção")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/curso/recursos")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Recurso> findRecursoSemAssociacao(){
        return this.cursoService.findRecursoSemColecao();
    }
}
