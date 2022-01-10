package com.devappcorp.projetodevappcorp.services;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;

import java.util.List;


public interface AuthorService {
    void addNewAuthor(Author author);

    void updateAuthor(Long id, Author author);

    void deleteAuthor(Long id);

    List<Author> getAllAuthor();

    List<Author> findSobrenome(String sobrenome);

    List<Recurso> findAuthorRecusos(Long id);
}
