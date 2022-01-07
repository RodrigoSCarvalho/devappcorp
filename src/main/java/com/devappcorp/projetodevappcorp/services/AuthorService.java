package com.devappcorp.projetodevappcorp.services;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;

import java.util.List;


public interface AuthorService {
    String addNewAuthor(Author author);

    String updateAuthor(Long id, Author author);

    String deleteAuthor(Long id);

    List<Author> getAllAuthor();

    List<Author> findSobrenome(String sobrenome);

    List<Recurso> findAuthorRecusos(Long id);
}
