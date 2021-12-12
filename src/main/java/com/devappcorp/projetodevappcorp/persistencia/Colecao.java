package com.devappcorp.projetodevappcorp.persistencia;

import javax.persistence.*;
import java.util.List;

@Entity
public class Colecao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToMany
    @JoinTable(name = "associacao_colecao_recurso",
            joinColumns = @JoinColumn(name = "fk_colecao"),
            inverseJoinColumns = @JoinColumn(name = "fk_recurso"))
    @OrderBy("titulo asc")
    private List<Recurso> recursos;
    @Column(length = 1024)
    private String titulo;
    @Column(length = 4096)
    private String descricao;
    private String imagem;

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Colecao{" +
                "recursos=" + recursos +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", imagem='" + imagem + '\'' +
                '}';
    }
}
