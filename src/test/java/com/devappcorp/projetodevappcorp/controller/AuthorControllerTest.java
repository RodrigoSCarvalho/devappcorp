package com.devappcorp.projetodevappcorp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private AuthorService service;

	private static String authorId;
	
	@Test
	@Order(1)
    public void addNewAuthorTest() throws Exception {
		
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
		
		assertNotNull(newAuthorJson.get("id"));
		assertEquals("Author name", newAuthorJson.get("nome"));
		assertEquals("Author lastname", newAuthorJson.get("sobrenome"));
		assertEquals("author@email.com", newAuthorJson.get("email"));
		assertEquals("Universidade Federal Fluminense", newAuthorJson.get("afiliacao"));
		assertEquals("0000-0000-0000-0000", newAuthorJson.get("orcid"));
		
    }
	
	@Test
	@Order(2)
	public void testUpdateAuthor() throws Exception {
		
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
	
}
