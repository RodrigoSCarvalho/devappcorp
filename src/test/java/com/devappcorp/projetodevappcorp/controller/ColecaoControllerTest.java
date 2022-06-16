package com.devappcorp.projetodevappcorp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
public class ColecaoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@Order(1)
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
		
		mockMvc.perform(post("/colecao")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(collection1)))
		.andExpect(status().isCreated())
		.andReturn();
		
		//ADICIONAR VERIFICAÇÃO DOS DADOS CRIADOS
	
	}
	
	@Test
	@Order(2)
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
	
	//TESTE DE EXCLUSÃO PRECISA DO ID CRIADO
	
}
