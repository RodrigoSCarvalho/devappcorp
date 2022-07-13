package com.devappcorp.projetodevappcorp.mutation;

import com.devappcorp.projetodevappcorp.entities.Author;
import org.junit.jupiter.api.*;

//import org.testng.annotations.Test;

import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static com.devappcorp.projetodevappcorp.services.validations.Validations.validaEmail;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Testar validação de Author")
public class MutationTests {

//    @BeforeMethod
//    public void setup(){
//        Author author = new Author();
//    }
//    @BeforeEach
//    public void setup(){
//
//    }

    @Test
    @DisplayName("Testar validação de e-mail com um caracter")
    public void validaEmailAuthorTest() {
        Author author = new Author();
        author.setId(1L);
        author.setNome("Author name");
        author.setSobrenome("Author lastname");
        author.setEmail("a"); //e
        author.setOrcid("0000-0000-0000-0000");
        author.setAfiliacao("UFF");

//        assertEquals("Author name", author.getNome());
//        assertEquals("Author lastname", author.getSobrenome());
        //assertTrue();
        assertEquals(false,validaEmail(author.getEmail()),"email com 1 caracter apenas");
//        assertEquals("0000-0000-0000-0000", author.getOrcid());
//        assertEquals("UFF", author.getAfiliacao());
//        assertEquals("Author{id=1, orcid='0000-0000-0000-0000', email='author@email.com', nome='Author name', sobrenome='Author lastname', afiliacao='UFF'}", author.toString());

    }
}

