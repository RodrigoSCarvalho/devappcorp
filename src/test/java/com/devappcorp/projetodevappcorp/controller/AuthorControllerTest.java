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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
		
		assertNotNull(newAuthorJson.get("id"));
		assertEquals(newAuthorJson.get("nome"), "Author name");
		assertEquals(newAuthorJson.get("sobrenome"), "Author lastname");
		assertEquals(newAuthorJson.get("email"), "author@email.com");
		assertEquals(newAuthorJson.get("afiliacao"), "Universidade Federal Fluminense");
		assertEquals(newAuthorJson.get("orcid"), "0000-0000-0000-0000");
		
    }
	
}
