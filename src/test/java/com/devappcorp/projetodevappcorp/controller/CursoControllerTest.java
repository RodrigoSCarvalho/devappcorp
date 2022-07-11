package com.devappcorp.projetodevappcorp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.devappcorp.projetodevappcorp.entities.Curso;
import com.devappcorp.projetodevappcorp.entities.Recurso;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/** CursoControllerTest class. */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Testar CursoController")
public class CursoControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  private static String courseId;
  
  private static List<String> resourcesAssocietedWithCourse;

  @Test
  @Order(1)
  @DisplayName("Testar a criação de um curso")
  public void addCourseTest() throws Exception {
    
    Curso course1 = new Curso();    
    
    course1.setTitulo("Course title");
    course1.setDescricao("Course description");
    course1.setData_registro("16-06-2022");
    course1.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");

    Recurso resource1 = new Recurso();
    
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
    
    course1.setRecursos(resources);
    
    MvcResult newCourse = mockMvc.perform(post("/curso")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(course1))).andExpect(status()
        .isCreated())
        .andReturn();
  
    JSONObject newCourseJson = new JSONObject(newCourse.getResponse().getContentAsString());    
    
    assertNotNull(newCourseJson.get("id"));
    assertEquals("Course title", newCourseJson.get("titulo"));
    assertEquals("Course description", newCourseJson.get("descricao"));
    assertEquals("16-06-2022", newCourseJson.get("data_registro"));
    assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", newCourseJson.get("imagem"));
    
    JSONArray courseResource = new JSONArray(newCourseJson.get("recursos").toString());
    
    assertEquals(1, courseResource.length());

    resourcesAssocietedWithCourse = new ArrayList<String>();
    
    for (int i = 0; i < courseResource.length(); i++) {
      JSONObject courseResourceJson = courseResource.getJSONObject(i);
      
      resourcesAssocietedWithCourse.add(courseResourceJson.getString("id"));
      
      assertEquals("2022-06-15", courseResourceJson.get("data_criacao"));
      assertEquals("2022-06-16", courseResourceJson.get("data_registro"));
      assertEquals("Resource title", courseResourceJson.get("titulo"));
      assertEquals("Resource description", courseResourceJson.get("descricao"));
      assertEquals("https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg", courseResourceJson.get("imagem"));
      assertEquals("https://getbootstrap.com/docs/5.0/getting-started/introduction/", courseResourceJson.get("link"));
    }

  }
  
  @Test
  @Order(2)
  @DisplayName("Testar a criação de um curso com um recurso existente")
  public void addCourseWithExistingResourceTest() throws Exception {
    
    Curso course2 = new Curso();
    
    course2.setTitulo("Course title");
    course2.setDescricao("Course description");
    course2.setData_registro("16-06-2022");
    course2.setImagem("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png");
    
    MvcResult newCourse = mockMvc.perform(post("/recurso/1/curso")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(course2))).andExpect(status()
        .isCreated())
        .andReturn();
  
    JSONObject newCourseJson = new JSONObject(newCourse.getResponse().getContentAsString());
    
    courseId = newCourseJson.getString("id");
    
    assertNotNull(newCourseJson.get("id"));
    assertEquals("Course title", newCourseJson.get("titulo"));
    assertEquals("Course description", newCourseJson.get("descricao"));
    assertEquals("16-06-2022", newCourseJson.get("data_registro"));
    assertEquals("https://miro.medium.com/max/460/1*ahIiDbsR6s9XgR45nJJ5DA.png", newCourseJson.get("imagem"));
    
    JSONArray courseResource = new JSONArray(newCourseJson.get("recursos").toString());
    
    assertEquals(1, courseResource.length());

    for (int i = 0; i < courseResource.length(); i++) {
      JSONObject courseResourceJson = courseResource.getJSONObject(i);
      
      assertNotNull(courseResourceJson.get("data_criacao"));
      assertNotNull(courseResourceJson.get("data_registro"));
      assertNotNull(courseResourceJson.get("titulo"));
      assertNotNull(courseResourceJson.get("descricao"));
      assertNotNull(courseResourceJson.get("imagem"));
      assertNotNull(courseResourceJson.get("link"));
    }

  }
  
  @Test
  @Order(3)
  @DisplayName("Testar a busca por recursos de um curso")
  public void findResourcesByCourse() throws Exception {
    
    MvcResult resources = mockMvc.perform(get("/curso/" + courseId + "/recursos")
        .contentType("application/json")).andExpect(status().isAccepted()).andReturn();
    
    JSONArray resourcesJson = new JSONArray(resources.getResponse().getContentAsString());
    
    assertTrue(resourcesJson.length() > 0);
    
  }
  
  @Test
  @Order(4)
  @DisplayName("Testar a atualização de um curso")
  public void updateCourseTest() throws Exception {
    
    Curso courseToUpdate = new Curso();
    
    courseToUpdate.setTitulo("New course title");
    courseToUpdate.setDescricao("New course description");
    
    MvcResult updatedCourse = mockMvc.perform(put("/curso/" + courseId)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(courseToUpdate))).andExpect(status()
        .isOk())
        .andReturn();
    
    JSONObject updatedCourseJson = new JSONObject(updatedCourse.getResponse().getContentAsString());
    
    assertEquals("New course title", updatedCourseJson.get("titulo"));
    assertEquals("New course description", updatedCourseJson.get("descricao"));
    
  }
  
  @Test
  @Order(5)
  @DisplayName("Testar a busca por todos os cursos")
  public void getAllCoursesTest() throws Exception {
    
    MvcResult courses = mockMvc.perform(get("/curso")
        .contentType("application/json")).andExpect(status().isOk()).andReturn();
    
    JSONArray coursesJsonArray = new JSONArray(courses.getResponse().getContentAsString());
    
    assertTrue(coursesJsonArray.length() > 0);
    
  }
  
  @Test
  @Order(6)
  @DisplayName("Testar a busca por cursos recentes (últimos 5)")
  public void getRecentCoursesTest() throws Exception {
    
    MvcResult courses = mockMvc.perform(get("/curso/recentes")
        .contentType("application/json")).andExpect(status().isOk()).andReturn();
    
    JSONArray coursesJsonArray = new JSONArray(courses.getResponse().getContentAsString());
    
    assertTrue(coursesJsonArray.length() > 0);
    assertTrue(coursesJsonArray.length() <= 5);
    
  }
  
  @Test
  @Order(7)
  @DisplayName("Testar a busca por um curso")
  public void getCourseByIdTest() throws Exception {
    
    MvcResult course = mockMvc.perform(get("/curso/" + courseId)
        .contentType("application/json")).andExpect(status().isAccepted()).andReturn();
    
    JSONObject courseJson = new JSONObject(course.getResponse().getContentAsString());
    
    assertEquals("New course title", courseJson.get("titulo"));
    assertEquals("New course description", courseJson.get("descricao"));
    
  }
  
  @Test
  @Order(8)
  @DisplayName("Testar a atualização de um curso existente adicionando um recurso existente")
  public void updateCollectionWithExistingResource() throws Exception {
    
    mockMvc.perform(put("/recurso/1/curso/" + courseId)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(new Curso()))).andExpect(status()
        .isOk())
    .andReturn();
    
  }
  
  @Test
  @Order(9)
  @DisplayName("Testar a busca por recursos que não estão associados a coleção")
  public void findResourceWithoutCollection() throws Exception {
    
    MvcResult resources = mockMvc.perform(get("/curso/recursos")
        .contentType("application/json")).andExpect(status().isAccepted()).andReturn();
    
    JSONArray resourcesArray = new JSONArray(resources.getResponse().getContentAsString());
    
    for (int i = 0; i < resourcesArray.length(); i++) {
      
      for (int j = 0; i < resourcesAssocietedWithCourse.size(); i++) {
        
        assertNotEquals(resourcesArray.get(i), resourcesAssocietedWithCourse.get(j));
        
      }
      
    }
    
  }
  
  @Test
  @Order(10)
  @DisplayName("Testar a exclusão de um curso")
  public void deleteCourseTest() throws Exception {
    
    mockMvc.perform(delete("/curso/" + courseId)
        .contentType("application/json"))
    .andExpect(status().isOk()).andReturn();

  }
  
}