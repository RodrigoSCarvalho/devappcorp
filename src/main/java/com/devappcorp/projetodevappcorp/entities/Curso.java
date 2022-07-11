package com.devappcorp.projetodevappcorp.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import javax.validation.constraints.Pattern;
import java.io.Serializable;



/** Curso class. */
@Entity
@PrimaryKeyJoinColumn(name = "cursoId")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Curso extends Colecao implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(length = 10)
  private String data_registro;


    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message="O formato da data está incoreta ")
    public String getData_registro() {
        return data_registro;
    }


  public void setData_registro(String data_registro) {
    this.data_registro = data_registro;
  }

  @Override
  public String toString() {
    return "Curso{"
         + "data_registro='" + data_registro + '\''
         + '}';
  }
}
