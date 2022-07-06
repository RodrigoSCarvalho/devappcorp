package com.devappcorp.projetodevappcorp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testar entidade Recurso")
public class RecursoTest {

	@Test
	@DisplayName("Testar instanciação de Recurso")
	public void instantiateEventTest() {
		
		Recurso resource = new Recurso();
		
		resource.setId(1L);
		resource.setTitulo("Resource title");
		resource.setDescricao("Resource description");
		resource.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
		resource.setLink("https://getbootstrap.com/docs/5.0/getting-started/introduction/");
		resource.setData_criacao("2022-06-10");
		resource.setData_registro("2022-06-15");
		
		List<String> keyWords = new ArrayList<String>();
		
		keyWords.add("frontend");
		keyWords.add("html");
		keyWords.add("css");		
		
		resource.setPalavras_chave(keyWords);
		
		assertEquals("Resource title", resource.getTitulo());
		assertEquals("Resource description", resource.getDescricao());
		assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", resource.getImagem());
		assertEquals("https://getbootstrap.com/docs/5.0/getting-started/introduction/", resource.getLink());
		assertEquals("2022-06-10", resource.getData_criacao());
		assertEquals("2022-06-15", resource.getData_registro());
		assertEquals("Recurso{autores=[], palavras_chave=[frontend, html, css], titulo='Resource title', descricao='Resource description', link='https://getbootstrap.com/docs/5.0/getting-started/introduction/', imagem='https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png', data_criacao='2022-06-10', data_registro='2022-06-15'}", resource.toString());
		
	}
	
}
