package com.devappcorp.projetodevappcorp.controller;


import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping // Map ONLY POST Requests
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewAuthor(@RequestBody Author author ) {

        return this.authorService.addNewAuthor(author);

    }

    @PutMapping("{id}") // Map ONLY POST Requests
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String updateAuthor(@PathVariable Long id, @RequestBody Author author){

        return this.authorService.updateAuthor(id, author);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String deleteAuthor(@PathVariable Long id){
        return this.authorService.deleteAuthor(id);

    }

    @GetMapping
    public @ResponseBody List<Author> getAllAuthor() {
        // This returns a JSON or XML with the users
        return this.authorService.getAllAuthor();
    }

    @GetMapping("{sobrenome}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Author> findSobrenome(@PathVariable String sobrenome) {
        return this.authorService.findSobrenome(sobrenome);
    }

    @GetMapping("{id}/recursos")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Recurso> findAuthorRecusos(@PathVariable Long id){
    return this.authorService.findAuthorRecusos(id);

    }
}