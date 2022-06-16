package com.devappcorp.projetodevappcorp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
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
public class ColecaoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void addNewColecaoTest() throws Exception {
		
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
	
}
