package com.devappcorp.projetodevappcorp.controller;


import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.services.AuthorService;
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
@RequestMapping("/author") // endpoint direto
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
    public ResponseEntity addNewAuthor( @Valid @RequestBody  Author author ) {

        this.authorService.addNewAuthor(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);

    }


    @Operation(summary = "Atualizar um autor")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Autor atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @PutMapping("{id}") // Map ONLY POST Requests
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Author> updateAuthor( @Valid @PathVariable Long id, @RequestBody Author author){

        this.authorService.updateAuthor(id, author);
        return new ResponseEntity<>(author, HttpStatus.OK);

    }

    @Operation(summary = "Deletar um autor")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Autor deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteAuthor(@PathVariable Long id){
        this.authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
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
    @Operation(summary = "Recuperar autor pelo id")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "400", description = "Erro de validação.")
    })
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Optional<Author> findAuthorById(@PathVariable Long id){
        return this.authorService.findAuthorById(id);

    }

}