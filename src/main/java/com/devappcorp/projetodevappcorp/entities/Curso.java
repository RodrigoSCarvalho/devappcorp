package com.devappcorp.projetodevappcorp.entities;

import com.devappcorp.projetodevappcorp.entities.Colecao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;


@Entity
@PrimaryKeyJoinColumn(name = "cursoId")
public class Curso extends Colecao implements Serializable {

    private static final long serialVersionUID = 1L;

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
