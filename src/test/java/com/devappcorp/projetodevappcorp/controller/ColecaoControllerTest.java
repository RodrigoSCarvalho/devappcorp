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
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.devappcorp.projetodevappcorp.entities.Colecao;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Testar ColecaoController")
public class ColecaoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	private static String collectionId;
	
	@Test
	@Order(1)
	@DisplayName("Testar a criação de uma coleção")
	public void addNewCollectionTest() throws Exception {
		
		Colecao collection1 = new Colecao();
		Recurso resource1 = new Recurso();
		
		collection1.setTitulo("Collection title");
		collection1.setDescricao("Collection description");
		collection1.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
		
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
		
		collection1.setRecursos(resources);
		
		MvcResult newCollection = mockMvc.perform(post("/colecao")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(collection1)))
		.andExpect(status().isCreated())
		.andReturn();
		
		JSONObject newCollectionJson = new JSONObject(newCollection.getResponse().getContentAsString());
		
		assertNotNull(newCollectionJson .get("id"));
		assertEquals("Collection title", newCollectionJson .get("titulo"));
		assertEquals("Collection description", newCollectionJson .get("descricao"));
		assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", newCollectionJson .get("imagem"));
		
		JSONArray collectionResources = new JSONArray(newCollectionJson.get("recursos").toString());
		
		assertEquals(1, collectionResources.length());

		for (int i = 0; i < collectionResources.length(); i++) {
			JSONObject collectionResourceJson = collectionResources.getJSONObject(i);
			
			assertEquals("2022-06-15", collectionResourceJson.get("data_criacao"));
			assertEquals("2022-06-16", collectionResourceJson.get("data_registro"));
			assertEquals("Resource title", collectionResourceJson.get("titulo"));
			assertEquals("Resource description", collectionResourceJson.get("descricao"));
			assertEquals("https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg", collectionResourceJson.get("imagem"));
			assertEquals("https://getbootstrap.com/docs/5.0/getting-started/introduction/", collectionResourceJson.get("link"));
		}
	
	}
	
	@Test
	@Order(2)
	@DisplayName("Testar a atualização de uma coleção")
	public void updateCollectionTest() throws Exception {
		
		Colecao collectionToUpdate = new Colecao();
		
		collectionToUpdate.setTitulo("New collection title");
		collectionToUpdate.setDescricao("New collection description");
		collectionToUpdate.setImagem("https://matriculas.estacio.br/blog/wp-content/uploads/2019/08/ciencia-da-computacao-o-que-se-aprende-faculdade-estacio.jpg");
		
		MvcResult updatedCollection = mockMvc.perform(put("/colecao/1")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(collectionToUpdate)))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONObject updatedCollectionJson = new JSONObject(updatedCollection.getResponse().getContentAsString());
		
		assertEquals("New collection title", updatedCollectionJson.get("titulo"));
		assertEquals("New collection description", updatedCollectionJson.get("descricao"));
		assertEquals("https://matriculas.estacio.br/blog/wp-content/uploads/2019/08/ciencia-da-computacao-o-que-se-aprende-faculdade-estacio.jpg", updatedCollectionJson.get("imagem"));
				
	}
	
	@Test
	@Order(3)
	@DisplayName("Testar a busca por todas as coleções")
	public void getAllCollectionsTest() throws Exception {
		
		MvcResult collection = mockMvc.perform(get("/colecao")
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
		JSONArray collectionJsonArray = new JSONArray(collection.getResponse().getContentAsString());
		
		assertTrue(collectionJsonArray.length() > 0);
		
	}
	
	@Test
	@Order(4)
	@DisplayName("Testar a adição de uma coleção em um recurso existente")
	public void addNewCollectionWithExistingResource() throws Exception {
		
		Colecao collection2 = new Colecao();
		
		collection2.setTitulo("Collection title 2");
		collection2.setDescricao("Collection description 2");
		collection2.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
		
		MvcResult newCollection = mockMvc.perform(post("/recurso/1/colecao")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(collection2)))
		.andExpect(status().isCreated())
		.andReturn();
		
		JSONObject newCollectionJson = new JSONObject(newCollection.getResponse().getContentAsString());
		
		collectionId = newCollectionJson.getString("id");
		
		assertEquals("Collection title 2", newCollectionJson.get("titulo"));
		assertEquals("Collection description 2", newCollectionJson.get("descricao"));
		assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", newCollectionJson.get("imagem"));
		
		JSONArray collectionResources = new JSONArray(newCollectionJson.get("recursos").toString());
		
		assertEquals(1, collectionResources.length());

		for (int i = 0; i < collectionResources.length(); i++) {
			JSONObject collectionResourceJson = collectionResources.getJSONObject(i);
			
			assertNotNull(collectionResourceJson.get("data_criacao"));
			assertNotNull(collectionResourceJson.get("data_registro"));
			assertNotNull(collectionResourceJson.get("titulo"));
			assertNotNull(collectionResourceJson.get("descricao"));
			assertNotNull(collectionResourceJson.get("imagem"));
			assertNotNull(collectionResourceJson.get("link"));
		}
		
	}
	
	@Test
	@Order(5)
	@DisplayName("Testar a buscar de recursos por coleção")
	public void findResourcesByCollection() throws Exception {		

		MvcResult resources = mockMvc.perform(get("/colecao/" + collectionId + "/recursos")
				.contentType("application/json"))
		.andExpect(status().isAccepted())
		.andReturn();
		
		JSONArray collectionResources = new JSONArray(resources.getResponse().getContentAsString());
	
		assertTrue(collectionResources.length() > 0);

		for (int i = 0; i < collectionResources.length(); i++) {
			JSONObject collectionResourceJson = collectionResources.getJSONObject(i);
			
			assertNotNull(collectionResourceJson.get("data_criacao"));
			assertNotNull(collectionResourceJson.get("data_registro"));
			assertNotNull(collectionResourceJson.get("titulo"));
			assertNotNull(collectionResourceJson.get("descricao"));
			assertNotNull(collectionResourceJson.get("imagem"));
			assertNotNull(collectionResourceJson.get("link"));
		}
		
	}
	
	@Test
	@Order(6)
	@DisplayName("Testar a atualização de uma coleção existente adicionando um recurso existente")
	public void updateCollectionWithExistingResource() throws Exception {
		
		mockMvc.perform(put("/recurso/1/colecao/" + collectionId)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(new Colecao())))
		.andExpect(status().isOk())
		.andReturn();
		
	}
	
	@Test
	@Order(7)
	@DisplayName("Testar a exclusão de uma coleção")
	public void deleteCollectionTest() throws Exception {
		
		mockMvc.perform(delete("/colecao/" + collectionId)
				.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn();
		
	}
	
}
