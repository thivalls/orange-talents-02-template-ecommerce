package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.user.User;
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
class ProductControllerTest {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    void deveEncontrarRotaPostProducts() throws Exception {
//        ProductRequest product = new ProductRequest();
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(product))
//        ).andExpect(MockMvcResultMatchers.status().isOk());
//    }
}