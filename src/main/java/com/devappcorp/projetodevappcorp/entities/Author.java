package com.devappcorp.projetodevappcorp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "orcid", unique = true, length = 19)
    private String orcid;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(unique = true)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(length = 64)
    private String nome;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(length = 64)
    private String sobrenome;
    @Column(length = 256)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String afiliacao;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "recurso_autores",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "recurso_id"))
    //@JsonIgnore
    private Set<Recurso> recursos = new HashSet<Recurso>();


    public Set<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(Set<Recurso> recursos) {
        this.recursos = recursos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        String arroba = "@";
        int caracter = 0;
        boolean tamanho = false;


        if (email.length() > 3) {
            tamanho = true;
        }

        for (int i = 0; i < email.length(); i++) {
            if ('@' == email.charAt(i)) {
                caracter +=1;
            }
        }


        if (tamanho==true && caracter == 1)
            this.email = email;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getAfiliacao() {
        return afiliacao;
    }

    public void setAfiliacao(String afiliacao) {
        this.afiliacao = afiliacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrcid() {
        return orcid;
    }

    public void setOrcid(String orcid) {
        this.orcid = orcid;
    }

    public Author() {
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", orcid='" + orcid + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", afiliacao='" + afiliacao + '\'' +
                '}';
    }

    public void set(Long id, Author author) {
        this.email = author.getEmail();
        this.nome = author.getNome();
        this.sobrenome = author.getSobrenome();
        this.orcid = author.getOrcid();
        this.afiliacao = author.getAfiliacao();
        this.recursos = author.getRecursos();
    }
}
