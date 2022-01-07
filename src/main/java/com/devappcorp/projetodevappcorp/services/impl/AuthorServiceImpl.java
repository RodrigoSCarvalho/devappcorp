package com.devappcorp.projetodevappcorp.services.impl;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.repositories.AuthorRepository;
import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.repositories.RecursoRepository;
import com.devappcorp.projetodevappcorp.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private RecursoRepository recursoRepository;

    @Override
    public String addNewAuthor(Author author) {

        /*
        System.out.println("===="+author.getRecursos().get(0).getId());
        List<Recurso> r = author.getRecursos();

        for (Recurso recurso : r){
            recursoRepository.findById(recurso.getId()).map(recursoExistente ->{
                recursoExistente.setId(recurso.getId());

                return recursoRepository.save(recursoExistente);
            });

        }
        author.setRecursos(r);


        Author a = new Author();

        a.setId(author.getId());
        a.setOrcid(author.getOrcid());
        a.setRecursos(author.getRecursos());
        a.setAfiliacao(author.getAfiliacao());
        a.setNome(author.getNome());
        a.setSobrenome(author.getSobrenome());

         */


        authorRepository.save(author);
        return "Salvo!";
    }


    @Override
    public String updateAuthor(Long id, Author author) {
        authorRepository.findById(id).map(authorExistente -> {
            authorExistente.setOrcid(author.getOrcid());
            authorExistente.setAfiliacao(author.getAfiliacao());
            authorExistente.setNome(author.getNome());
            authorExistente.setSobrenome(author.getSobrenome());
            authorExistente.setEmail(author.getEmail());
            authorExistente.setRecursos(author.getRecursos());

            return authorRepository.save(authorExistente);

        });

        return "Salvo!";
    }

    @Override
    public String deleteAuthor(Long id) {
        authorRepository.findById(id).map(authorExistente -> {
            authorRepository.delete(authorExistente);
            return authorExistente;
        });

        return "Excluído!";
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
}
