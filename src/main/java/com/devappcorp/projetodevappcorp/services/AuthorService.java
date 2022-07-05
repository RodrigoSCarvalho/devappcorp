package com.devappcorp.projetodevappcorp.services;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;

import java.util.List;
import java.util.Optional;


public interface AuthorService {
    Author addNewAuthor(Author author);

    void updateAuthor(Long id, Author author);

    void deleteAuthor(Long id);

    List<Author> getAllAuthor();

    List<Author> findSobrenome(String sobrenome);

    List<Recurso> findAuthorRecusos(Long id);

    Optional<Author> findAuthorById(Long id);
}
