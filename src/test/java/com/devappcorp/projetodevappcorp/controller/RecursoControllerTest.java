package com.devappcorp.projetodevappcorp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
@DisplayName("Testar EventoController")
public class RecursoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private static String authorId;
	private static String resourceId;
	
	@Test
	@Order(1)
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
	
}
