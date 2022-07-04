package com.devappcorp.projetodevappcorp.service;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.repositories.AuthorRepository;
import com.devappcorp.projetodevappcorp.services.impl.AuthorServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AutorService {

    @Autowired
    AuthorRepository _authorRepository;

    @Test
    public void countRows(){
        Author a = new Author();
        a.setEmail("klone");
        _authorRepository.save(a);
    }
}
