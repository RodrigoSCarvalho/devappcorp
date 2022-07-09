package com.devappcorp.projetodevappcorp.service;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.repositories.AuthorRepository;
import com.devappcorp.projetodevappcorp.services.impl.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AutorService {

    @Autowired
    AuthorRepository _authorRepository;

    @Autowired
    AuthorServiceImpl _authorService;

    @Test
    public void setEmail(){
        Author a = new Author();
        a.setEmail("klone1@gmail.com");
        Author autor = _authorService.addNewAuthor(a);
        System.out.println(autor.getEmail());
        System.out.println(autor);
        assertEquals("klone1@gmail.com", autor.getEmail());
    }

    @Test
    public void setOrcid(){
        Author a = new Author();
        a.setOrcid("0000-0000-0000-0025");
        Author autor = _authorService.addNewAuthor(a);
        System.out.println(autor.getOrcid());
        System.out.println(autor);
        assertEquals("0000-0000-0000-0025", autor.getOrcid());
    }
}
