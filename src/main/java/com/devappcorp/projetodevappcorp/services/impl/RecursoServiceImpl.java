package com.devappcorp.projetodevappcorp.services.impl;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.devappcorp.projetodevappcorp.repositories.AuthorRepository;
import com.devappcorp.projetodevappcorp.repositories.RecursoRepository;
import com.devappcorp.projetodevappcorp.services.AuthorService;
import com.devappcorp.projetodevappcorp.services.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RecursoServiceImpl implements RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Override
    public void updateRecurso(Long id, Recurso recurso) {
        recursoRepository.findById(id).map(recursoExisente -> {
            recursoExisente.setTitulo(recurso.getTitulo());
            recursoExisente.setLink(recurso.getLink());
            recursoExisente.setPalavras_chave(recurso.getPalavras_chave());
            recursoExisente.setDescricao(recurso.getDescricao());
            recursoExisente.setImagem(recurso.getImagem());
            recursoExisente.setData_registro(recurso.getData_registro());
            recursoExisente.setData_criacao(recurso.getData_criacao());
            recursoExisente.setColecao(recurso.getColecao());

            return recursoRepository.save(recursoExisente);

        });
    }

    @Override
    public void updateAutorRecurso(Long authorId, Long recursoId, Recurso recurso) {
        recursoRepository.findById(recursoId).map(recursoExisente -> {
            recursoExisente.setTitulo(recurso.getTitulo());
            recursoExisente.setLink(recurso.getLink());
            recursoExisente.setPalavras_chave(recurso.getPalavras_chave());
            recursoExisente.setDescricao(recurso.getDescricao());
            recursoExisente.setImagem(recurso.getImagem());
            recursoExisente.setData_registro(recurso.getData_registro());
            recursoExisente.setData_criacao(recurso.getData_criacao());
            recursoExisente.setColecao(recurso.getColecao());


            authorRepository.findById(authorId).map(author -> {
                author.getRecursos().add(recursoExisente);
                return authorRepository.save(author);
            });


            return recursoRepository.save(recursoExisente);
        });
    }

    @Override
    public void deleteRecurso(Long id) {



        recursoRepository.findById(id).map(recursoExistente -> {
                for (Author author : recursoExistente.getAutores()) {
                    author.getRecursos().remove(recursoExistente);
                    authorRepository.save(author);
                }
                recursoRepository.delete(recursoExistente);
                return recursoExistente;
        });
    }

    @Override
    public void addNewRecurso(Recurso recurso, Long authorId) {
        authorRepository.findById(authorId).map(authorExistente -> {
            recurso.getAutores().add(authorExistente);
            authorExistente.getRecursos().add(recurso);

            return authorRepository.save(authorExistente);
            //return recursoRepository.save(recurso);
        });
    }

    @Override
    public List<Recurso> getAllRecurso() {
        return recursoRepository.findAll();
    }

    @Override
    public List<Recurso> findTop5Recursos() {
        return recursoRepository.findTop5ByOrderByIdDesc();
    }


    @Override
    public List<Recurso> getAllRecursoPeloTitulo(String titulo) {
        return (List<Recurso>) recursoRepository.findAllByTitulo(titulo);
    }

    @Override
    public Optional<Recurso> findRecursoById(Long id) {
        return recursoRepository.findById(id);
    }

    @Override
    public List<String> findPalavrasChaveById(Long id) {
        return recursoRepository.findPalavrasChaveById(id);
    }

    @Override
    public List<Author> findAutoresDoRecurso(Long id) {
        return authorRepository.findAutoresDoRecurso(id);
    }
}
