package br.com.zup.mercadolivre.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCadastrarNovoUsuario() throws Exception {
        UserRequest user = new UserRequest("teste@teste.com.br", "123456");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
        ).andExpect(
                MockMvcResultMatchers.status().is(200)
        );

        List<User> users = em.createQuery("select u from User u", User.class).getResultList();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, users.size()),
                () -> Assertions.assertEquals("teste@teste.com.br", users.get(0).getEmail())
        );
    }

    @Test
    void naoDeveCadastrarNovoUsuarioComEmailRepetido() throws Exception {
        UserRequest user = new UserRequest("teste@teste.com.br", "123456");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
        ).andExpect(
                MockMvcResultMatchers.status().is(200)
        );

        List<User> users = em.createQuery("select u from User u", User.class).getResultList();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, users.size())
        );
    }

}