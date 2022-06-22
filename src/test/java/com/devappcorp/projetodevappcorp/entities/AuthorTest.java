package com.devappcorp.projetodevappcorp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testar entidade Author")
public class AuthorTest {

	@Test
	@DisplayName("Testar instanciação de Author")
	public void instantiateAuthorTest() {
		
		Author author = new Author();
		
		author.setId(1L);
		author.setNome("Author name");
		author.setSobrenome("Author lastname");
		author.setEmail("author@email.com");
		author.setOrcid("0000-0000-0000-0000");
		author.setAfiliacao("UFF");
		
		assertEquals("Author name", author.getNome());
		assertEquals("Author lastname", author.getSobrenome());
		assertEquals("author@email.com", author.getEmail());
		assertEquals("0000-0000-0000-0000", author.getOrcid());
		assertEquals("UFF", author.getAfiliacao());
		assertEquals("Author{id=1, orcid='0000-0000-0000-0000', email='author@email.com', nome='Author name', sobrenome='Author lastname', afiliacao='UFF'}", author.toString());
		
	}
	
}
