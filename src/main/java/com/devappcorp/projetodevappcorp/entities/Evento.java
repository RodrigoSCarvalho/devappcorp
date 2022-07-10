package com.devappcorp.projetodevappcorp.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/** Evento class. */
@Entity
@PrimaryKeyJoinColumn(name = "eventoId")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Evento extends Colecao implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column (length = 10)
  private String data_criacao;
  @Column(length = 10)
  private String data_fim;

  public String getData_criacao() {
    return data_criacao;
  }

  public void setData_criacao(String data_criacao) {
    this.data_criacao = data_criacao;
  }

  public String getData_fim() {
    return data_fim;
  }

  public void setData_fim(String data_fim) {
    this.data_fim = data_fim;
  }

  @Override
  public String toString() {
    return "Evento{"
        + "data_criacao='" + data_criacao + '\''
        + ", data_fim='" + data_fim + '\''
        + '}';
  }
}
