package br.com.zup.mercadolivre.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase()
@SpringBootTest
@ActiveProfiles("test")
class CategoryControllerTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    @Transactional
//    void deveCadastrarNovaCategoria() throws Exception {
//        CategoryRequest category = new CategoryRequest("Nova categoria teste", 1l);
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/categories")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(category))
//        ).andExpect(
//                MockMvcResultMatchers.status().is(200)
//        );
//
//        List<Category> categories = em.createQuery("select u from Category u", Category.class).getResultList();
//
//        Assertions.assertAll(
//                () -> Assertions.assertEquals(1, categories.size()),
//                () -> Assertions.assertEquals("Category 1", categories.get(0).getName()),
//                () -> Assertions.assertEquals("1", categories.get(0).getId())
//        );
//    }

//    @Test
//    void deveCadastrarNovaCategoriaComCategoriaMae() throws Exception {
//        CategoryRequest category = new CategoryRequest("Categoryy 1", null);
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/categories")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(user))
//        ).andExpect(
//                MockMvcResultMatchers.status().is(200)
//        );
//
//        List<Category> categories = em.createQuery("select u from Category u", Category.class).getResultList();
//
//        Assertions.assertAll(
//                () -> Assertions.assertEquals(1, categories.size()),
//                () -> Assertions.assertEquals("Category", categories.get(0).getName())
//        );
//    }

//    @Test
//    void naoDeveCadastrarNovoUsuarioComEmailRepetido() throws Exception {
//        UserRequest user = new UserRequest("teste@teste.com.br", "123456");
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(user))
//        ).andExpect(
//                MockMvcResultMatchers.status().is(200)
//        );
//
//        List<User> users = em.createQuery("select u from User u", User.class).getResultList();
//
//        Assertions.assertAll(
//                () -> Assertions.assertEquals(1, users.size())
//        );
//    }

}