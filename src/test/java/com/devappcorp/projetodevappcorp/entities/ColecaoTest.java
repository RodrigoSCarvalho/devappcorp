package com.devappcorp.projetodevappcorp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** ColecaoTest class. */
@DisplayName("Testar entidade Colecao")
public class ColecaoTest {

  @Test
  @DisplayName("Testar instanciação de Colecao")
  public void instantiateCollectionTest() {
    
    Colecao collection = new Colecao();
    
    collection.setId(1L);
    collection.setTitulo("Collection title");
    collection.setDescricao("Collection description");
    collection.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
    
    assertEquals("Collection title", collection.getTitulo());
    assertEquals("Collection description", collection.getDescricao());
    assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", collection.getImagem());
    assertEquals("Colecao{id=1, recursos=[], titulo='Collection title', descricao='Collection description', imagem='https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png'}", collection.toString());

  }
  
}
