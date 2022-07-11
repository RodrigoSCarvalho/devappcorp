package com.devappcorp.projetodevappcorp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ElementCollection
    @CollectionTable (name = "recurso_palavras_chave",
            joinColumns = @JoinColumn(name = "recurso_id"))
    @Column (name = "palavras_chave")

    private List<String> palavras_chave;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToMany(mappedBy = "recursos")
    @JsonIgnore
    private Set<Author> autores = new HashSet<Author>();


    public Recurso() {}


    @Column (length=1024)
    private String titulo;
    @Column (length=4096)
    private String descricao;
    private String link;
    private String imagem;

    @Column (length=10)
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message="O formato da data está incoreta ")
    private String data_criacao;

    @Column (length=10)
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message="O formato da data está incoreta ")
    private String data_registro;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn ( name = "colecao_id")
    private Colecao colecao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Author> getAutores() {
        return autores;
    }

    public List<String> getPalavras_chave() {
        return palavras_chave;
    }

    public void setPalavras_chave(List<String> palavras_chave) {
        this.palavras_chave = palavras_chave;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(String data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getData_registro() {
        return data_registro;
    }

    public void setData_registro(String data_registro) {
        this.data_registro = data_registro;
    }

    public Colecao getColecao() {
        return colecao;
    }

    public void setColecao(Colecao colecao) {
        this.colecao = colecao;
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
