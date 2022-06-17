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
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.devappcorp.projetodevappcorp.entities.Evento;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class EventoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private static String eventId;

	@Test
	@Order(1)
	public void addEventTest() throws Exception {
		
		Evento event1 = new Evento();
		Recurso resource1 = new Recurso();
		
		event1.setTitulo("Event title");
		event1.setDescricao("Event description");
		event1.setData_criacao("16-06-2022");
		event1.setData_fim("26-06-2022");
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
		assertEquals("16-06-2022", newEventJson.get("data_criacao"));
		assertEquals("26-06-2022", newEventJson.get("data_fim"));
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
	public void addEventWithExistingResourceTest() throws Exception {
		
		Evento event2 = new Evento();
		
		event2.setTitulo("Event title");
		event2.setDescricao("Event description");
		event2.setData_criacao("16-06-2022");
		event2.setData_fim("26-06-2022");
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
		assertEquals("16-06-2022", newEventJson.get("data_criacao"));
		assertEquals("26-06-2022", newEventJson.get("data_fim"));
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
	public void findResourcesByEvent() throws Exception {
		
		MvcResult resources = mockMvc.perform(get("/evento/" + eventId + "/recursos")
				.contentType("application/json"))
		.andExpect(status().isAccepted())
		.andReturn();
		
		JSONArray resourcesJson = new JSONArray(resources.getResponse().getContentAsString());
		
		assertTrue(resourcesJson.length() > 0);
		
	}
//	
//	@Test
//	@Order(4)
//	public void updateCourseTest() throws Exception {
//		
//		Curso courseToUpdate = new Curso();
//		
//		courseToUpdate.setTitulo("New course title");
//		courseToUpdate.setDescricao("New course description");
//		
//		MvcResult updatedCurso = mockMvc.perform(put("/curso/" + courseId)
//				.contentType("application/json")
//				.content(objectMapper.writeValueAsString(courseToUpdate)))
//		.andExpect(status().isOk())
//		.andReturn();
//		
//		JSONObject updatedCourseJson = new JSONObject(updatedCurso.getResponse().getContentAsString());
//
//		assertEquals("New course title", updatedCourseJson.get("titulo"));
//		assertEquals("New course description", updatedCourseJson.get("descricao"));
//		
//	}
////	
////	@Test
////	@Order(4)
////	@DisplayName("Testar a atualização de uma curso existente adicionando um recurso existente")
////	public void updateCollectionWithExistingResource() throws Exception {
////		
////		MvcResult updatedCollection = mockMvc.perform(put("/recurso/1/curso/" + courseId)
////				.contentType("application/json"))
////		.andExpect(status().isOk())
////		.andReturn();
////		
////	}
//	
//	@Test
//	@Order(5)
//	public void getAllCoursesTest() throws Exception {
//		
//		MvcResult courses = mockMvc.perform(get("/curso")
//				.contentType("application/json"))
//		.andExpect(status().isOk())
//		.andReturn();
//		
//		JSONArray coursesJsonArray = new JSONArray(courses.getResponse().getContentAsString());
//		
//		assertTrue(coursesJsonArray.length() > 0);
//		
//	}
//	
//	@Test
//	@Order(6)
//	public void getRecentCoursesTest() throws Exception {
//		
//		MvcResult courses = mockMvc.perform(get("/curso/recentes")
//				.contentType("application/json"))
//		.andExpect(status().isOk())
//		.andReturn();
//		
//		JSONArray coursesJsonArray = new JSONArray(courses.getResponse().getContentAsString());
//		
//		assertTrue(coursesJsonArray.length() > 0);
//		assertTrue(coursesJsonArray.length() <= 5);
//		
//	}
//	
//	@Test
//	@Order(7)
//	public void getCourseByIdTest() throws Exception {
//		
//		MvcResult course = mockMvc.perform(get("/curso/" + courseId)
//				.contentType("application/json"))
//		.andExpect(status().isAccepted())
//		.andReturn();
//		
//		JSONObject courseJson = new JSONObject(course.getResponse().getContentAsString());
//		
//		assertEquals("New course title", courseJson.get("titulo"));
//		assertEquals("New course description", courseJson.get("descricao"));
//		
//	}
//	
//	//ADICIONAR TESTE PARA RECURSO SEM ASSOCIAÇÃO
//	
//	@Test
//	@Order(8)
//	public void deleteCourseTest() throws Exception {
//		
//		mockMvc.perform(delete("/curso/" + courseId)
//				.contentType("application/json"))
//		.andExpect(status().isOk())
//		.andReturn();
//
//	}
	
}