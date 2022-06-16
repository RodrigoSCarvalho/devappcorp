package com.devappcorp.projetodevappcorp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.devappcorp.projetodevappcorp.entities.Author;
import com.devappcorp.projetodevappcorp.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	@Test
    void testAddNewAuthor() throws Exception {
		
		Author author1 = new Author();
		
		author1.setId((long) 1);
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
		
		assertEquals(newAuthorJson.get("id"), 1);
		assertEquals(newAuthorJson.get("nome"), "Author name");
		assertEquals(newAuthorJson.get("sobrenome"), "Author lastname");
		assertEquals(newAuthorJson.get("email"), "author@email.com");
		assertEquals(newAuthorJson.get("afiliacao"), "Universidade Federal Fluminense");
		assertEquals(newAuthorJson.get("orcid"), "0000-0000-0000-0000");
		
    }
	
	@Test
	public void testUpdateAuthor() throws Exception {
		
		Author authorToUpdate = new Author();
		
		authorToUpdate.setNome("New author name");
		authorToUpdate.setSobrenome("New author lastname");
		authorToUpdate.setEmail("newauthor@email.com");
		authorToUpdate.setAfiliacao("UFF");
		authorToUpdate.setOrcid("0000-0000-0000-0001");
		
		MvcResult updatedAuthor = mockMvc.perform(put("/author/1")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(authorToUpdate)))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONObject updatedAuthorJson = new JSONObject(updatedAuthor.getResponse().getContentAsString());
		
		assertEquals(updatedAuthorJson.get("nome"), "New author name");
		assertEquals(updatedAuthorJson.get("sobrenome"), "New author lastname");
		assertEquals(updatedAuthorJson.get("email"), "newauthor@email.com");
		assertEquals(updatedAuthorJson.get("afiliacao"), "UFF");
		assertEquals(updatedAuthorJson.get("orcid"), "0000-0000-0000-0001");
		
	}
	
}
