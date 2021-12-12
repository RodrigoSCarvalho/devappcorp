package com.devappcorp.projetodevappcorp.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Evento extends Colecao  {

    @Column (length=10)
    private String data_criacao;
    @Column(length=10)
    private String data_fim;

    @Override
    public String toString() {
        return "Evento{" +
                "data_criacao='" + data_criacao + '\'' +
                ", data_fim='" + data_fim + '\'' +
                '}';
    }
}
