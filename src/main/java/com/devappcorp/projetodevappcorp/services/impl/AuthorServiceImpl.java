package com.devappcorp.projetodevappcorp.services.impl;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.repositories.AuthorRepository;
import com.devappcorp.projetodevappcorp.repositories.RecursoRepository;
import com.devappcorp.projetodevappcorp.services.AuthorService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.devappcorp.projetodevappcorp.services.validations.Validations.validaEmail;
import static com.devappcorp.projetodevappcorp.services.validations.Validations.validaOrcid;


/**
 * AuthorServiceImpl class.
 */

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private RecursoRepository recursoRepository;


    @Override
    public Author addNewAuthor(Author author) {
        String email = author.getEmail();
        String orcid = author.getOrcid();
        boolean emailValido = validaEmail(email);
        boolean orcidValido = validaOrcid(orcid);

        if (!emailValido || !orcidValido)
            throw new Error();

        return authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Long id, Author author) {

        authorRepository.findById(id).map(authorExistente -> {
            String email = author.getEmail();
            String orcid = author.getOrcid();
            boolean emailValido = validaEmail(email);
            boolean orcidValido = validaOrcid(orcid);

            if (!emailValido)
                throw new Error();
            else
                authorExistente.setEmail(author.getEmail());

            if (author.getSobrenome() != null)
                authorExistente.setSobrenome(author.getSobrenome());
            if (author.getAfiliacao() != null)
                authorExistente.setAfiliacao(author.getAfiliacao());
            if (author.getNome() != null)
                authorExistente.setNome(author.getNome());

            if (!orcidValido)
                throw new Error();
            else
                authorExistente.setOrcid(author.getOrcid());

            if (!author.getRecursos().isEmpty())
                authorExistente.setRecursos((author.getRecursos()));

            authorRepository.save(authorExistente);

            return ResponseEntity.noContent().build();
        });

    }

//  @Override
//  public void updateAuthor(Long id, Author author) {
//    authorRepository.findById(id).map(authorExistente -> {
//      if (author.getEmail() != null) {
//        authorExistente.setEmail(author.getEmail());
//      }
//      if (author.getSobrenome() != null) {
//        authorExistente.setSobrenome(author.getSobrenome());
//      }
//      if (author.getAfiliacao() != null) {
//        authorExistente.setAfiliacao(author.getAfiliacao());
//      }
//      if (author.getNome() != null) {
//        authorExistente.setNome(author.getNome());
//      }
//      if (author.getOrcid() != null) {
//        authorExistente.setOrcid(author.getOrcid());
//      }
//      if (!author.getRecursos().isEmpty()) {
//        authorExistente.setRecursos((author.getRecursos()));
//      }
//
//      authorRepository.save(authorExistente);
//
//      return ResponseEntity.noContent().build();
//
//    });
//  }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.findById(id).map(authorExistente -> {
            List<Integer> recursos = authorRepository.findRecursoIds(id);
            if (!recursos.isEmpty()) {
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
        return authorRepository.findAllBySobrenomeOrderByNome(sobrenome);
    }

    @Override
    public List<Recurso> findAuthorRecusos(Long id) {
        return authorRepository.findAuthorRecursos(id);
    }

    @Override
    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }
}
