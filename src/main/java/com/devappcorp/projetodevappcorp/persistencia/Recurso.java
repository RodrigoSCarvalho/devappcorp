package com.devappcorp.projetodevappcorp.persistencia;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToMany
    @JoinTable (name = "recurso_autores",
            joinColumns = @JoinColumn(name = "fk_recurso"),
            inverseJoinColumns = @JoinColumn(name = "fk_autor"))
    private List<Author> autores;

    @ElementCollection
    @CollectionTable (name = "recurso_palavras_chave",
    joinColumns = @JoinColumn(name = "recurso_id"))
    @Column (name = "palavras_chave")
    private List<String> palavras_chave;
    @Column (length=1024)
    private String titulo;
    @Column (length=4096)
    private String descricao;
    private String link;
    private String imagem;
    @Column (length=10)
    private String data_criacao;
    @Column (length=10)
    private String data_registro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Recurso{" +
                "autores=" + autores +
                ", palavras_chave=" + palavras_chave +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", link='" + link + '\'' +
                ", imagem='" + imagem + '\'' +
                ", data_criacao='" + data_criacao + '\'' +
                ", data_registro='" + data_registro + '\'' +
                '}';
    }
}
