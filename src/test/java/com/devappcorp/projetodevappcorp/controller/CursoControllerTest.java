package com.devappcorp.projetodevappcorp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CursoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void addCourseTest() throws Exception {
		
		Curso course1 = new Curso();
		Recurso resource1 = new Recurso();
		
		course1.setTitulo("Course title");
		course1.setDescricao("Course description");
		course1.setData_registro("16-06-2022");
		course1.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");

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
		
		course1.setRecursos(resources);
		
		MvcResult newCourse = mockMvc.perform(post("/curso")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(course1)))
		.andExpect(status().isCreated())
		.andReturn();
	
		JSONObject newCourseJson = new JSONObject(newCourse.getResponse().getContentAsString());		
		
		assertNotNull(newCourseJson.get("id"));
		assertEquals("Course title", newCourseJson.get("titulo"));
		assertEquals("Course description", newCourseJson.get("descricao"));
		assertEquals("16-06-2022", newCourseJson.get("data_registro"));
		assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", newCourseJson.get("imagem"));
		
		JSONArray courseResource = new JSONArray(newCourseJson.get("recursos").toString());
		
		assertTrue(courseResource.length() == 1);

		for (int i = 0; i < courseResource.length(); i++) {
			JSONObject courseResourceJson = courseResource.getJSONObject(i);
			
			assertEquals("2022-06-15", courseResourceJson.get("data_criacao"));
			assertEquals("2022-06-16", courseResourceJson.get("data_registro"));
			assertEquals("Resource title", courseResourceJson.get("titulo"));
			assertEquals("Resource description", courseResourceJson.get("descricao"));
			assertEquals("https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg", courseResourceJson.get("imagem"));
			assertEquals("https://getbootstrap.com/docs/5.0/getting-started/introduction/", courseResourceJson.get("link"));
		}

	}
	
	@Test
	public void addCourseWithExistingResourceTest() throws Exception {
		
		Curso course2 = new Curso();
		
		course2.setTitulo("Course title");
		course2.setDescricao("Course description");
		course2.setData_registro("16-06-2022");
		course2.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
		
		MvcResult newCourse = mockMvc.perform(post("/recurso/1/curso")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(course2)))
		.andExpect(status().isCreated())
		.andReturn();
	
		JSONObject newCourseJson = new JSONObject(newCourse.getResponse().getContentAsString());
		
		assertNotNull(newCourseJson.get("id"));
		assertEquals("Course title", newCourseJson.get("titulo"));
		assertEquals("Course description", newCourseJson.get("descricao"));
		assertEquals("16-06-2022", newCourseJson.get("data_registro"));
		assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", newCourseJson.get("imagem"));
		
		JSONArray courseResource = new JSONArray(newCourseJson.get("recursos").toString());
		
		assertTrue(courseResource.length() == 1);

		for (int i = 0; i < courseResource.length(); i++) {
			JSONObject courseResourceJson = courseResource.getJSONObject(i);
			
			assertNotNull(courseResourceJson.get("data_criacao"));
			assertNotNull(courseResourceJson.get("data_registro"));
			assertNotNull(courseResourceJson.get("titulo"));
			assertNotNull(courseResourceJson.get("descricao"));
			assertNotNull(courseResourceJson.get("imagem"));
			assertNotNull(courseResourceJson.get("link"));
		}

	}
	
}
