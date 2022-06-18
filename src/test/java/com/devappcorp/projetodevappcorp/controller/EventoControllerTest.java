package com.devappcorp.projetodevappcorp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Evento;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Testar EventoController")
public class EventoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private static String eventId;

	@Test
	@Order(1)
	@DisplayName("Testar a criação de um evento")
	public void addEventTest() throws Exception {
		
		Evento event1 = new Evento();
		Recurso resource1 = new Recurso();
		
		event1.setTitulo("Event title");
		event1.setDescricao("Event description");
		event1.setData_criacao("2022-06-16");
		event1.setData_fim("2022-06-26");
		event1.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");

		resource1.setTitulo("Resource title");
		resource1.setDescricao("Resource description");
		resource1.setData_criacao("2022-06-15");
		resource1.setData_registro("2022-06-16");
		resource1.setImagem("https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg");
		resource1.setLink("https://getbootstrap.com/docs/5.0/getting-started/introduction/");
		
		List<String> keyWords = new ArrayList<String>();
		
		keyWords.add("frontend");
		keyWords.add("html");
		keyWords.add("css");
		
		resource1.setPalavras_chave(keyWords);
		
		Set<Recurso> resources = new HashSet<Recurso>();
		resources.add(resource1);
		
		event1.setRecursos(resources);
		
		MvcResult newEvent = mockMvc.perform(post("/evento")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(event1)))
		.andExpect(status().isCreated())
		.andReturn();
	
		JSONObject newEventJson = new JSONObject(newEvent.getResponse().getContentAsString());		
		
		assertNotNull(newEventJson.get("id"));
		assertEquals("Event title", newEventJson.get("titulo"));
		assertEquals("Event description", newEventJson.get("descricao"));
		assertEquals("2022-06-16", newEventJson.get("data_criacao"));
		assertEquals("2022-06-26", newEventJson.get("data_fim"));
		assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", newEventJson.get("imagem"));
		
		JSONArray eventResource = new JSONArray(newEventJson.get("recursos").toString());
		
		assertTrue(eventResource.length() == 1);

		for (int i = 0; i < eventResource.length(); i++) {
			JSONObject eventResourceJson = eventResource.getJSONObject(i);
			
			assertEquals("2022-06-15", eventResourceJson.get("data_criacao"));
			assertEquals("2022-06-16", eventResourceJson.get("data_registro"));
			assertEquals("Resource title", eventResourceJson.get("titulo"));
			assertEquals("Resource description", eventResourceJson.get("descricao"));
			assertEquals("https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg", eventResourceJson.get("imagem"));
			assertEquals("https://getbootstrap.com/docs/5.0/getting-started/introduction/", eventResourceJson.get("link"));
		}

	}
	
	@Test
	@Order(2)
	@DisplayName("Testar a criação de um evento com um recurso existente")
	public void addEventWithExistingResourceTest() throws Exception {
		
		Evento event2 = new Evento();
		
		event2.setTitulo("Event title");
		event2.setDescricao("Event description");
		event2.setData_criacao("2022-06-16");
		event2.setData_fim("2022-06-26");
		event2.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
		
		MvcResult newEvent = mockMvc.perform(post("/recurso/1/evento")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(event2)))
		.andExpect(status().isCreated())
		.andReturn();
	
		JSONObject newEventJson = new JSONObject(newEvent.getResponse().getContentAsString());
		
		eventId = newEventJson.getString("id");
		
		assertNotNull(newEventJson.get("id"));
		assertEquals("Event title", newEventJson.get("titulo"));
		assertEquals("Event description", newEventJson.get("descricao"));
		assertEquals("2022-06-16", newEventJson.get("data_criacao"));
		assertEquals("2022-06-26", newEventJson.get("data_fim"));
		assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", newEventJson.get("imagem"));
		
		JSONArray eventResource = new JSONArray(newEventJson.get("recursos").toString());
		
		assertTrue(eventResource.length() == 1);

		for (int i = 0; i < eventResource.length(); i++) {
			JSONObject eventResourceJson = eventResource.getJSONObject(i);
			
			assertNotNull(eventResourceJson.get("data_criacao"));
			assertNotNull(eventResourceJson.get("data_registro"));
			assertNotNull(eventResourceJson.get("titulo"));
			assertNotNull(eventResourceJson.get("descricao"));
			assertNotNull(eventResourceJson.get("imagem"));
			assertNotNull(eventResourceJson.get("link"));
		}

	}
	
	@Test
	@Order(3)
	@DisplayName("Testar a busca por recursos de um evento")
	public void getResourcesByEvent() throws Exception {
		
		MvcResult resources = mockMvc.perform(get("/evento/" + eventId + "/recursos")
				.contentType("application/json"))
		.andExpect(status().isAccepted())
		.andReturn();
		
		JSONArray resourcesJson = new JSONArray(resources.getResponse().getContentAsString());
		
		assertTrue(resourcesJson.length() > 0);
		
	}

	@Test
	@Order(4)
	@DisplayName("Testar a busca por eventos em período de tempo")
	public void getEventByPeriod() throws Exception {
	
		MvcResult event = mockMvc.perform(get("/evento/20220616/20220626")
				.contentType("application/json"))
		.andExpect(status().isAccepted())
		.andReturn();
		
		JSONArray eventResources = new JSONArray(event.getResponse().getContentAsString());
		
		assertTrue(eventResources.length() > 0);

		for (int i = 0; i < eventResources.length(); i++) {
			JSONObject eventResourceJson = eventResources.getJSONObject(i);
			
			assertNotNull(eventResourceJson.get("data_criacao"));
			assertNotNull(eventResourceJson.get("data_fim"));
			assertNotNull(eventResourceJson.get("titulo"));
			assertNotNull(eventResourceJson.get("descricao"));
			assertNotNull(eventResourceJson.get("imagem"));
		}
		
	}
	
	@Test
	@Order(5)
	@DisplayName("Testar a busca por um evento")
	public void getEventByIdTest() throws Exception {
		
		MvcResult event = mockMvc.perform(get("/evento/" + eventId)
				.contentType("application/json"))
		.andExpect(status().isAccepted())
		.andReturn();
		
		JSONObject eventJson = new JSONObject(event.getResponse().getContentAsString());
		
		assertEquals("New event title", eventJson.get("titulo"));
		assertEquals("New event description", eventJson.get("descricao"));
		
	}
	
	@Test
	@Order(6)
	@DisplayName("Testar a atualização de um evento")
	public void updateEventTest() throws Exception {
		
		Evento eventToUpdate = new Evento();
		
		eventToUpdate.setTitulo("New event title");
		eventToUpdate.setDescricao("New event description");
		
		MvcResult updatedEvent = mockMvc.perform(put("/evento/" + eventId)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(eventToUpdate)))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONObject updatedEventJson = new JSONObject(updatedEvent.getResponse().getContentAsString());
		
		assertEquals("New event title", updatedEventJson.get("titulo"));
		assertEquals("New event description", updatedEventJson.get("descricao"));
		assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", updatedEventJson.get("imagem"));
		
	}
	
	@Test
	@Order(7)
	@DisplayName("Testar a atualização de um evento existente adicionando um recurso existente")
	public void updateEventWithExistingResource() throws Exception {
		
		MvcResult updatedEvent = mockMvc.perform(put("/recurso/1/evento/" + eventId)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(new Evento())))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONObject updatedEventJson = new JSONObject(updatedEvent.getResponse().getContentAsString());
		
		assertEquals("New event title", updatedEventJson.get("titulo"));
		assertEquals("New event description", updatedEventJson.get("descricao"));
		assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", updatedEventJson.get("imagem"));
		
	}

	@Test
	@Order(8)
	@DisplayName("Testar a busca por todos os eventos")
	public void getAllEventsTest() throws Exception {
		
		MvcResult events = mockMvc.perform(get("/evento")
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONArray eventsJsonArray = new JSONArray(events.getResponse().getContentAsString());
		
		assertTrue(eventsJsonArray.length() > 0);
		
	}
	
	@Test
	@Order(9)
	@DisplayName("Testar a busca por eventos recentes (últimos 5)")
	public void getRecentEventsTest() throws Exception {
		
		MvcResult events = mockMvc.perform(get("/evento/recentes")
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONArray eventsJsonArray = new JSONArray(events.getResponse().getContentAsString());
		
		assertTrue(eventsJsonArray.length() > 0);
		assertTrue(eventsJsonArray.length() <= 5);
		
	}
	
	//ADICIONAR TESTE PARA RECURSO SEM ASSOCIAÇÃO

	@Test
	@Order(10)
	@DisplayName("Testar a exclusão de um evento")
	public void deleteEventTest() throws Exception {
		
		mockMvc.perform(delete("/evento/" + eventId)
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();

	}
	
}