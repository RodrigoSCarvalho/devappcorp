package com.devappcorp.projetodevappcorp.services.impl;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.repositories.AuthorRepository;
import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.repositories.RecursoRepository;
import com.devappcorp.projetodevappcorp.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private RecursoRepository recursoRepository;


    @Override
    public void addNewAuthor(Author author) {
        authorRepository.save(author);
    }


    @Override
    public void updateAuthor(Long id, Author author) {
        authorRepository.findById(id).map(authorExistente -> {
            authorExistente.setOrcid(author.getOrcid());
            authorExistente.setAfiliacao(author.getAfiliacao());
            authorExistente.setNome(author.getNome());
            authorExistente.setSobrenome(author.getSobrenome());
            authorExistente.setEmail(author.getEmail());
            authorExistente.setRecursos(author.getRecursos());

            return authorRepository.save(authorExistente);

        });
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.findById(id).map(authorExistente -> {
            List<Integer> recursos = authorRepository.findRecursoIds(id);
            if (recursos.size() > 0) {
                for (int i = 0; i < recursos.size(); i++) {
                    recursoRepository.findById(Long.valueOf(recursos.get(i))).map(recursoExistente -> {
                        for (Author author : recursoExistente.getAutores()) {
                            author.getRecursos().remove(recursoExistente);
                            authorRepository.save(author);
                        }
                        recursoRepository.delete(recursoExistente);
                        return recursoExistente;
                    });
                }
            }

            authorRepository.delete(authorExistente);
            return authorExistente;
        });
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }


    @Override
    public List<Author> findSobrenome(String sobrenome) {
        return (List<Author>) authorRepository.findAllBySobrenomeOrderByNome(sobrenome);
    }

    @Override
    public List<Recurso> findAuthorRecusos(Long id) {
        return (List<Recurso>) authorRepository.findAuthorRecursos(id);
    }

    @Override
    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }
}
