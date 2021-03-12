package br.com.zup.mercadolivre.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("It should create a new category")
    void mustCreateNewCategoryWithoutParent() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest("Category", null);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/categories")
                    .content(objectMapper.writeValueAsString(categoryRequest))
                    .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("It should not create a new category with non existent parent id")
    void shouldNotCreateNewCategoryWithNonExistentParent() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest("Esporte", 1L);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/categories")
                        .content(objectMapper.writeValueAsString(categoryRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("It should create a new category with existent parent category")
    void mustCreateNewCategoryWithExistentParent() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest("Esporte", null);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/categories")
                        .content(objectMapper.writeValueAsString(categoryRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        Query query = em.createQuery("select c from Category c", Category.class);
        System.out.println(query.getResultList().size());

        CategoryRequest categoryRequest1 = new CategoryRequest("Futebol", categoryRequest.getCategoryId());
        System.out.println(categoryRequest1.toString());
        mockMvc.perform(
                MockMvcRequestBuilders.post("/categories")
                        .content(objectMapper.writeValueAsString(categoryRequest1))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("It should not create a categories with equals name")
    void mustNotCreateCategoryWithEqualsName() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest("Esporte", null);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/categories")
                        .content(objectMapper.writeValueAsString(categoryRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        mockMvc.perform(
                MockMvcRequestBuilders.post("/categories")
                        .content(objectMapper.writeValueAsString(categoryRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}