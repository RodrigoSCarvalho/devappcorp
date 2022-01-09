package com.devappcorp.projetodevappcorp.controller;


import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Operation(summary = "Salvar um autor")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Autor cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PostMapping // Map ONLY POST Requests
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewAuthor(@RequestBody Author author ) {

        return this.authorService.addNewAuthor(author);

    }


    @Operation(summary = "Atualizar um autor")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Autor atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("{id}") // Map ONLY POST Requests
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String updateAuthor(@PathVariable Long id, @RequestBody Author author){

        return this.authorService.updateAuthor(id, author);

    }

    @Operation(summary = "Deletar um autor")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Autor deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String deleteAuthor(@PathVariable Long id){
        return this.authorService.deleteAuthor(id);

    }

    @Operation(summary = "Buscar todos autores")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping
    public @ResponseBody List<Author> getAllAuthor() {
        // This returns a JSON or XML with the users
        return this.authorService.getAllAuthor();
    }

    @Operation(summary = "Buscar todos autores com determinado sobrenome")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("/sn")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Author> findSobrenome(@RequestParam String sobrenome) {
        return this.authorService.findSobrenome(sobrenome);
    }

    @Operation(summary = "Buscar todos recursos de um autor")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("{id}/recursos")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Recurso> findAuthorRecusos(@PathVariable Long id){
    return this.authorService.findAuthorRecusos(id);

    }
}