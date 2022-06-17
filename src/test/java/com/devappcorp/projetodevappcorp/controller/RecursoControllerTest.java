package com.devappcorp.projetodevappcorp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Testar RecursoController")
public class RecursoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private static String authorId;
	private static String resourceId;
	
	@Test
	@Order(1)
	@DisplayName("Testar a criação de um recurso")
	public void addNewResourceTest() throws Exception {
		
		Author author1 = new Author();
		
		author1.setNome("Author name");
		author1.setSobrenome("Author lastname");
		author1.setEmail("author@email.com");
		author1.setAfiliacao("Universidade Federal Fluminense");
		author1.setOrcid("0000-0000-0000-0000");
		
		MvcResult newAuthor = mockMvc.perform(post("/author")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(author1)))
		.andExpect(status().isCreated())
		.andReturn();
		
		JSONObject newAuthorJson = new JSONObject(newAuthor.getResponse().getContentAsString());
		
		authorId = newAuthorJson.getString("id");
	
		Recurso resource1 = new Recurso();
		
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
		
		MvcResult newResource = mockMvc.perform(post("/author/" + authorId + "/recurso")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(resource1)))
		.andExpect(status().isCreated())
		.andReturn();
		
		JSONObject newResourceJson = new JSONObject(newResource.getResponse().getContentAsString());
		
		resourceId = newResourceJson.getString("id");
		
		assertEquals("2022-06-15", newResourceJson.get("data_criacao"));
		assertEquals("2022-06-16", newResourceJson.get("data_registro"));
		assertEquals("Resource title", newResourceJson.get("titulo"));
		assertEquals("Resource description", newResourceJson.get("descricao"));
		assertEquals("https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg", newResourceJson.get("imagem"));
		assertEquals("https://getbootstrap.com/docs/5.0/getting-started/introduction/", newResourceJson.get("link"));
		
	}
	
	@Test
	@Order(2)
	@DisplayName("Testar a busca por todos os recursos")
	public void getAllResourcesTest() throws Exception {
		
		MvcResult resources = mockMvc.perform(get("/recurso")
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONArray resourcesJsonArray = new JSONArray(resources.getResponse().getContentAsString());
		
		assertTrue(resourcesJsonArray.length() > 0);
		
	}
	
	@Test
	@Order(3)
	@DisplayName("Testar a busca por recursos recentes (últimos 5)")
	public void getRecentResourcesTest() throws Exception {
		
		MvcResult resources = mockMvc.perform(get("/recurso/recentes")
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONArray resourcesJsonArray = new JSONArray(resources.getResponse().getContentAsString());
		
		assertTrue(resourcesJsonArray.length() > 0);
		assertTrue(resourcesJsonArray.length() <= 5);
		
	}

//  ISSUE 10
//	@Test
//	@Order(4)
//	public void getCoursesNotAssociatedWithResource() throws Exception {
//		
//		Curso course1 = new Curso();
//		Curso course2 = new Curso();
//		Recurso resource1 = new Recurso();
//		
//		course1.setTitulo("Course title 1");
//		course1.setDescricao("Course description 1");
//		course1.setData_registro("16-06-2022");
//		course1.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
//		
//		course2.setTitulo("Course title 2");
//		course2.setDescricao("Course description 2");
//		course2.setData_registro("16-06-2022");
//		course2.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
//
//		resource1.setTitulo("Resource title");
//		resource1.setDescricao("Resource description");
//		resource1.setData_criacao("2022-06-15");
//		resource1.setData_registro("2022-06-16");
//		resource1.setImagem("https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg");
//		resource1.setLink("https://getbootstrap.com/docs/5.0/getting-started/introduction/");
//		
//		List<String> keyWords = new ArrayList<String>();
//		
//		keyWords.add("frontend");
//		keyWords.add("html");
//		keyWords.add("css");
//		
//		resource1.setPalavras_chave(keyWords);
//		
//		Set<Recurso> resources = new HashSet<Recurso>();
//		resources.add(resource1);
//		
//		course1.setRecursos(resources);
//		
//		MvcResult newCourse1 = mockMvc.perform(post("/curso")
//				.contentType("application/json")
//				.content(objectMapper.writeValueAsString(course1)))
//		.andExpect(status().isCreated())
//		.andReturn();
//	
//		mockMvc.perform(post("/curso")
//				.contentType("application/json")
//				.content(objectMapper.writeValueAsString(course2)))
//		.andExpect(status().isCreated())
//		.andReturn();
//	
//		JSONObject newCourse1Json = new JSONObject(newCourse1.getResponse().getContentAsString());
//		
//		String newCourse1Id = newCourse1Json.getString("id");
//		
//		MvcResult courses = mockMvc.perform(get("/recurso/cursos")
//				.contentType("application/json"))
//		.andExpect(status().isOk())
//		.andReturn();
//		
//		List<String> courseArray = Arrays.asList(courses.getResponse().getContentAsString().replace("[", "").replace("]", "").split(","));
//		
//		for (int i = 0; i < courseArray.size(); i++) {
//
//			assertTrue(!courseArray.get(i).equals(newCourse1Id));
//			
//		}
//		
//	}
	
//	ISSUE 11
//	@Test
//	@Order(4)
//	public void getEventsNotAssociatedWithResource() throws Exception {
//		
//		Evento event1 = new Evento();
//		Evento event2 = new Evento();
//		Recurso resource1 = new Recurso();
//		
//		event1.setTitulo("Event title 1");
//		event1.setDescricao("Event description 1");
//		event1.setData_criacao("16-06-2022");
//		event1.setData_fim("26-06-2022");
//		event1.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
//		
//		event2.setTitulo("Event title 2");
//		event2.setDescricao("Event description 2");
//		event2.setData_criacao("16-06-2022");
//		event2.setData_fim("26-06-2022");
//		event2.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
//
//		resource1.setTitulo("Resource title");
//		resource1.setDescricao("Resource description");
//		resource1.setData_criacao("2022-06-15");
//		resource1.setData_registro("2022-06-16");
//		resource1.setImagem("https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg");
//		resource1.setLink("https://getbootstrap.com/docs/5.0/getting-started/introduction/");
//		
//		List<String> keyWords = new ArrayList<String>();
//		
//		keyWords.add("frontend");
//		keyWords.add("html");
//		keyWords.add("css");
//		
//		resource1.setPalavras_chave(keyWords);
//		
//		Set<Recurso> resources = new HashSet<Recurso>();
//		resources.add(resource1);
//		
//		event1.setRecursos(resources);
//		
//		MvcResult newEvent1 = mockMvc.perform(post("/evento")
//				.contentType("application/json")
//				.content(objectMapper.writeValueAsString(event1)))
//		.andExpect(status().isCreated())
//		.andReturn();
//	
//		mockMvc.perform(post("/evento")
//				.contentType("application/json")
//				.content(objectMapper.writeValueAsString(event2)))
//		.andExpect(status().isCreated())
//		.andReturn();
//	
//		JSONObject newEvent1Json = new JSONObject(newEvent1.getResponse().getContentAsString());
//		
//		String newEvent1Id = newEvent1Json.getString("id");
//		
//		MvcResult events = mockMvc.perform(get("/recurso/eventos")
//				.contentType("application/json"))
//		.andExpect(status().isOk())
//		.andReturn();
//		
//		List<String> eventArray = Arrays.asList(events.getResponse().getContentAsString().replace("[", "").replace("]", "").split(","));
//		
//		for (int i = 0; i < eventArray.size(); i++) {
//
//			assertTrue(!eventArray.get(i).equals(newEvent1Id));
//			
//		}
//		
//	}
	
	@Test
	@Order(5)
	@DisplayName("Testar a exclusão de um recurso")
	public void deleteResourceTest() throws Exception {
		
//		mockMvc.perform(delete("/recurso/" + resourceId)
//				.contentType("application/json"))
//		.andExpect(status().isOk())
//		.andReturn();
		
		mockMvc.perform(delete("/author/" + authorId)
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
	}
	
//	PARA DESENVOLVER - DEPENDEM DO ID RETORNADO
	
//	findRecursoById
//	findPalavrasChave
//	findAutoresNotInRecurso
//	updateRecurso
//	dessassociarRecurso
//	AssociarRecurso
//	updateAutorRecurso
	
}
