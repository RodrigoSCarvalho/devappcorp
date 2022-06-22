package com.devappcorp.projetodevappcorp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testar entidade Evento")
public class EventoTest {

	@Test
	@DisplayName("Testar instanciação de Evento")
	public void instantiateEventTest() {
		
		Evento event = new Evento();
		
		event.setId(1L);
		event.setTitulo("Event title");
		event.setDescricao("Event description");
		event.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
		event.setData_criacao("2022-06-10");
		event.setData_fim("2022-06-15");
		
		assertEquals("Event title", event.getTitulo());
		assertEquals("Event description", event.getDescricao());
		assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", event.getImagem());
		assertEquals("2022-06-10", event.getData_criacao());
		assertEquals("2022-06-15", event.getData_fim());
		assertEquals("Evento{data_criacao='2022-06-10', data_fim='2022-06-15'}", event.toString());
		
	}
	
}
