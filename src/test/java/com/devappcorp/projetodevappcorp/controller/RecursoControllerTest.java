package com.devappcorp.projetodevappcorp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Evento;
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
	private static String authorId2;
	private static String resourceId;
	private static String courseId;
	
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
		
		assertNotEquals(newResourceJson.getString("id"), "null");
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

	@Test
	@Order(4)
	@DisplayName("Testar a busca por cursos que não estão associados a um recurso")
	public void getCoursesNotAssociatedWithResourceTest() throws Exception {
		
		Curso course = new Curso();
		
		course.setTitulo("Course title");
		course.setDescricao("Course description");
		course.setData_registro("16-06-2022");
		course.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");

		MvcResult newCourse = mockMvc.perform(post("/curso")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(course)))
		.andExpect(status().isCreated())
		.andReturn();
	
		JSONObject newCourseJson = new JSONObject(newCourse.getResponse().getContentAsString());
		
		String newCourseId = newCourseJson.getString("id");
		
		MvcResult courses = mockMvc.perform(get("/recurso/cursos")
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		List<String> coursesArray = Arrays.asList(courses.getResponse().getContentAsString().replace("[", "").replace("]", "").split(","));

		assertNotEquals(coursesArray.indexOf(newCourseId), -1);
		
	}
	
	@Test
	@Order(5)
	@DisplayName("Testar a busca por eventos que não estão associados a um recurso")
	public void getEventsNotAssociatedWithResourceTest() throws Exception {
		
		Evento event = new Evento();
		
		event.setTitulo("Event title");
		event.setDescricao("Event description");
		event.setData_criacao("16-06-2022");
		event.setData_fim("26-06-2022");
		event.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");

		MvcResult newEvent = mockMvc.perform(post("/evento")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(event)))
		.andExpect(status().isCreated())
		.andReturn();
	
		JSONObject newEventJson = new JSONObject(newEvent.getResponse().getContentAsString());
		
		String newEventId = newEventJson.getString("id");
		
		MvcResult events = mockMvc.perform(get("/recurso/eventos")
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		List<String> eventsArray = Arrays.asList(events.getResponse().getContentAsString().replace("[", "").replace("]", "").split(","));

		assertNotEquals(eventsArray.indexOf(newEventId), -1);
		
	}
	
	@Test
	@Order(6)
	@DisplayName("Testar a busca por um recurso")
	public void getResourceByIdTest() throws Exception {
		
		MvcResult resource = mockMvc.perform(get("/recurso/" + resourceId)
				.contentType("application/json"))
		.andExpect(status().isAccepted())
		.andReturn();
		
		JSONObject resourceJson = new JSONObject(resource.getResponse().getContentAsString());
		
		assertEquals("2022-06-15", resourceJson.get("data_criacao"));
		assertEquals("2022-06-16", resourceJson.get("data_registro"));
		assertEquals("Resource title", resourceJson.get("titulo"));
		assertEquals("Resource description", resourceJson.get("descricao"));
		assertEquals("https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg", resourceJson.get("imagem"));
		assertEquals("https://getbootstrap.com/docs/5.0/getting-started/introduction/", resourceJson.get("link"));
		
	}
	
	@Test
	@Order(7)
	@DisplayName("Testar a busca por palavras chaves de um recurso")
	public void getKeyWordsByResourceIdTest() throws Exception {
		
		MvcResult resource = mockMvc.perform(get("/recurso/" + resourceId + "/palavraschave")
				.contentType("application/json"))
		.andExpect(status().isAccepted())
		.andReturn();
		
		JSONArray keyWordsArray = new JSONArray(resource.getResponse().getContentAsString());
		
		assertEquals("frontend", keyWordsArray.get(0));
		assertEquals("html", keyWordsArray.get(1));
		assertEquals("css", keyWordsArray.get(2));
		
	}
	
	@Test
	@Order(8)
	@DisplayName("Testar a busca por autores sem recursos")
	public void getAuthorsNotAssociatedWithResourceTest() throws Exception {
		
		Author author1 = new Author();
		
		author1.setNome("Author name");
		author1.setSobrenome("Author lastname");
		author1.setEmail("author@email.com");
		author1.setAfiliacao("Universidade Federal Fluminense");
		author1.setOrcid("0000-0000-0000-0001");
		
		MvcResult newAuthor = mockMvc.perform(post("/author")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(author1)))
		.andExpect(status().isCreated())
		.andReturn();
		
		JSONObject newAuthorJson = new JSONObject(newAuthor.getResponse().getContentAsString());
		
		authorId2 = newAuthorJson.getString("id");
		
		String newAuthorId = newAuthorJson.getString("id");
		
		MvcResult authors = mockMvc.perform(get("/recurso/" + resourceId  + "/autores")
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONArray authorsArray = new JSONArray(authors.getResponse().getContentAsString());
		
		boolean authorInArray = false;
		
		for (int i = 0; i < authorsArray.length(); i++) {
			
			JSONObject authorJson = authorsArray.getJSONObject(i);
			
			if (authorJson.getString("id").equals(newAuthorId))
				authorInArray = true;
			
		}
		
		assertTrue(authorInArray);
		
	}
	
	@Test
	@Order(9)
	@DisplayName("Testar a atualização de um recurso")
	public void updateResourceTest() throws Exception {
		
		Recurso resourceToUpdate = new Recurso();
		
		resourceToUpdate.setTitulo("New resource title");
		resourceToUpdate.setDescricao("New resource description");
		
		MvcResult updatedResource = mockMvc.perform(put("/recurso/" + resourceId)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(resourceToUpdate)))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONObject updatedResourceJson = new JSONObject(updatedResource.getResponse().getContentAsString());
	
		assertEquals("New resource title", updatedResourceJson.get("titulo"));
		assertEquals("New resource description", updatedResourceJson.get("descricao"));
		
	}

	@Test
	@Order(10)
	@DisplayName("Testar a associação de um recurso a uma coleção")
	public void associatedCollectionResourceTest() throws Exception {
		
		Curso course1 = new Curso();
		
		course1.setTitulo("Course title 1");
		course1.setDescricao("Course description 1");
		course1.setData_registro("16-06-2022");
		course1.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
		
		MvcResult newCourse1 = mockMvc.perform(post("/curso")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(course1)))
		.andExpect(status().isCreated())
		.andReturn();
		
		JSONObject newCourse1Json = new JSONObject(newCourse1.getResponse().getContentAsString());
		
		courseId = newCourse1Json.getString("id");
		
		mockMvc.perform(get("/associar/" + courseId + "/recurso/" + resourceId)
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
			
	}
	
	@Test
	@Order(11)
	@DisplayName("Testar a desassociação de um recurso de uma coleção")
	public void unassociatedCollectionResourceTest() throws Exception {
		
		mockMvc.perform(get("/desassociar/" + courseId + "/recurso/" + resourceId)
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
	}

	@Test
	@Order(12)
	@DisplayName("Testar atualização de recurso existente com autor existente")
	public void updateAuthorResourceTest() throws Exception {
		
		mockMvc.perform(put("/author/" + authorId + "/recurso/" + resourceId)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(new Recurso())))
		.andExpect(status().isOk())
		.andReturn();
		
	}

	@Test
	@Order(13)
	@DisplayName("Testar a exclusão de um recurso")
	public void deleteResourceTest() throws Exception {
		
		mockMvc.perform(delete("/author/" + authorId)
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		mockMvc.perform(delete("/author/" + authorId2)
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		mockMvc.perform(delete("/recurso/" + resourceId)
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
	}
	
}
