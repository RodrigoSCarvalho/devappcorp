package com.devappcorp.projetodevappcorp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Testar AuthorController")
public class AuthorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private static String authorId;
	
	@Test
	@Order(1)
	@DisplayName("Testar a criação de um autor")
    public void addNewAuthorTest() throws Exception {
		
		Author author1 = new Author();
		Recurso resource1 = new Recurso();
		
		author1.setNome("Author name");
		author1.setSobrenome("Author lastname");
		author1.setEmail("author@email.com");
		author1.setAfiliacao("Universidade Federal Fluminense");
		author1.setOrcid("0000-0000-0000-0000");
		
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
		
		author1.setRecursos(resources);
		
		MvcResult newAuthor = mockMvc.perform(post("/author")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(author1)))
		.andExpect(status().isCreated())
		.andReturn();
		
		JSONObject newAuthorJson = new JSONObject(newAuthor.getResponse().getContentAsString());
		
		authorId = newAuthorJson.getString("id");
		
		assertNotNull(newAuthorJson.get("id"));
		assertEquals("Author name", newAuthorJson.get("nome"));
		assertEquals("Author lastname", newAuthorJson.get("sobrenome"));
		assertEquals("author@email.com", newAuthorJson.get("email"));
		assertEquals("Universidade Federal Fluminense", newAuthorJson.get("afiliacao"));
		assertEquals("0000-0000-0000-0000", newAuthorJson.get("orcid"));
		
		JSONArray authorResources = new JSONArray(newAuthorJson.get("recursos").toString());
		
		assertTrue(authorResources.length() == 1);

		for (int i = 0; i < authorResources.length(); i++) {
			JSONObject authorResourceJson = authorResources.getJSONObject(i);
			
			assertEquals("2022-06-15", authorResourceJson.get("data_criacao"));
			assertEquals("2022-06-16", authorResourceJson.get("data_registro"));
			assertEquals("Resource title", authorResourceJson.get("titulo"));
			assertEquals("Resource description", authorResourceJson.get("descricao"));
			assertEquals("https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg", authorResourceJson.get("imagem"));
			assertEquals("https://getbootstrap.com/docs/5.0/getting-started/introduction/", authorResourceJson.get("link"));
		}
		
    }
	
	@Test
	@Order(2)
	@DisplayName("Testar a atualização de um autor")
	public void updateAuthorTest() throws Exception {
		
		Author authorToUpdate = new Author();
		
		authorToUpdate.setNome("New author name");
		authorToUpdate.setSobrenome("lastname");
		authorToUpdate.setEmail("newauthor@email.com");
		authorToUpdate.setAfiliacao("UFF");
		authorToUpdate.setOrcid("0000-0000-0000-0001");
		
		MvcResult updatedAuthor = mockMvc.perform(put("/author/" + authorId)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(authorToUpdate)))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONObject updatedAuthorJson = new JSONObject(updatedAuthor.getResponse().getContentAsString());
		
		assertEquals("New author name", updatedAuthorJson.get("nome"));
		assertEquals("lastname", updatedAuthorJson.get("sobrenome"));
		assertEquals("newauthor@email.com", updatedAuthorJson.get("email"));
		assertEquals("UFF", updatedAuthorJson.get("afiliacao"));
		assertEquals("0000-0000-0000-0001", updatedAuthorJson.get("orcid"));
		
	}
		
	@Test
	@Order(3)
	@DisplayName("Testar a busca por um autor pelo seu id")
	public void findAuthorByIdTest() throws Exception {
		
		MvcResult author = mockMvc.perform(get("/author/" + authorId)
				.contentType("application/json"))
		.andExpect(status().isAccepted())
		.andReturn();
		
		JSONObject authorJson = new JSONObject(author.getResponse().getContentAsString());
		
		assertEquals("New author name", authorJson.get("nome"));
		assertEquals("lastname", authorJson.get("sobrenome"));
		assertEquals("newauthor@email.com", authorJson.get("email"));
		assertEquals("UFF", authorJson.get("afiliacao"));
		assertEquals("0000-0000-0000-0001", authorJson.get("orcid"));
		
	}
	
	@Test
	@Order(4)
	@DisplayName("Testar a busca por autores pelo seu sobrenome")
	public void findAuthorByLastNameTest() throws Exception {
		
		MvcResult author = mockMvc.perform(get("/author?sn=lastname")
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONArray authorsJsonArray = new JSONArray(author.getResponse().getContentAsString());
		
		assertTrue(authorsJsonArray.length() > 0);
		
		for (int i = 0; i < authorsJsonArray.length(); i++) {
			JSONObject authorJson = authorsJsonArray.getJSONObject(i);
			assertEquals("lastname", authorJson.get("sobrenome"));	
		}
		
	}
	
	@Test
	@Order(5)
	@DisplayName("Testar a busca por todos os autores")
	public void getAllAuthorsTest() throws Exception {
		
		MvcResult author = mockMvc.perform(get("/author")
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONArray authorsJsonArray = new JSONArray(author.getResponse().getContentAsString());
		
		assertTrue(authorsJsonArray.length() > 0);
		
	}
	
//	O RECURSO DO AUTOR ESTÁ SENDO EXCLUÍDO NA ATUALIZAÇÃO - CORRIGIR ATUALIZAÇÃO DE CAMPOS SEPARADADOS
//	@Test
//	@Order(6)
//	@DisplayName("Testar a busca pelos recursos de um autor")
//	public void findAuthorResourcesTest() throws Exception {
//		MvcResult author = mockMvc.perform(get("/author/" + authorId + "/recursos")
//				.contentType("application/json"))
//		.andExpect(status().isAccepted())
//		.andReturn();
//		
//		JSONArray authorResourcesJsonArray = new JSONArray(author.getResponse().getContentAsString());
//		
//		assertTrue(authorResourcesJsonArray.length() > 0);
//	}
	
	@Test
	@Order(7)
	@DisplayName("Testar a exclusão de um autor")
	public void deleteAuthorTest() throws Exception {
		
		mockMvc.perform(delete("/author/" + authorId)
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
	}
	
}
