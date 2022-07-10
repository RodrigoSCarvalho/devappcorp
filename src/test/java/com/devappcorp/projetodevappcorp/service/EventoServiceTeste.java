package com.devappcorp.projetodevappcorp.service;

import com.devappcorp.projetodevappcorp.entities.Evento;
import com.devappcorp.projetodevappcorp.services.EventoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** EventoServiceTest class. */
@SpringBootTest
public class EventoServiceTeste {

  @Autowired
  EventoService eventoService;

  @Test
  public void setDatas() {
    Evento e = new Evento();
    e.setData_criacao("2022-03-12");
    e.setData_fim("2022-04-01");
    Evento evento = eventoService.addEvento(e);
    Assertions.assertNotNull(evento);
  }
}
