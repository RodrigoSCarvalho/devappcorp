package com.devappcorp.projetodevappcorp.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Curso extends Colecao {

    @Column(length=10)
    private String data_registro;

    public String getData_registro() {
        return data_registro;
    }

    public void setData_registro(String data_registro) {
        this.data_registro = data_registro;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "data_registro='" + data_registro + '\'' +
                '}';
    }
}
