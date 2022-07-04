package com.devappcorp.projetodevappcorp.service;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.repositories.AuthorRepository;
import com.devappcorp.projetodevappcorp.services.impl.AuthorServiceImpl;
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AutorService {

    @Autowired
    AuthorRepository _authorRepository;

    @Test
    public void countRows(){
        Author a = new Author();
        a.setEmail("klone");
        Author autor = _authorRepository.save(a);
        System.out.println(autor.getEmail());
        assertEquals("klone", autor.getEmail());
    }
}
