package com.devappcorp.projetodevappcorp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Colecao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colecao")
    @OrderBy("titulo")
    private Set<Recurso> recursos = new HashSet<Recurso>();;
    @Column(length = 1024)
    private String titulo;
    @Column(length = 4096)
    private String descricao;
    private String imagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(Set<Recurso> recursos) {
        this.recursos = recursos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "Colecao{" +
                "id=" + id +
                ", recursos=" + recursos +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", imagem='" + imagem + '\'' +
                '}';
    }

}