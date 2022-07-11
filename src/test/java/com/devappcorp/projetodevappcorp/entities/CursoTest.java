package com.devappcorp.projetodevappcorp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** CursoTest class. */
@DisplayName("Testar entidade Curso")
public class CursoTest {

  @Test
  @DisplayName("Testar instanciação de Curso")
  public void instantiateCourseTest() {
    
    Curso course = new Curso();
    
    course.setId(1L);
    course.setTitulo("Collection title");
    course.setDescricao("Collection description");
    course.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
    course.setData_registro("2022-06-10");
    
    assertEquals("Collection title", course.getTitulo());
    assertEquals("Collection description", course.getDescricao());
    assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", course.getImagem());
    assertEquals("2022-06-10", course.getData_registro());
    assertEquals("Curso{data_registro='2022-06-10'}", course.toString());
    
  }
  
}
