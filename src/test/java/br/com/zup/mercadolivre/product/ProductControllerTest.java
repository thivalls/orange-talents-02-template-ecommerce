package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.category.CategoryRequest;
import br.com.zup.mercadolivre.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
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
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ProductControllerTest {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void itShouldCreateProduct() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest("Informatica", null);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/categories")
                        .content(objectMapper.writeValueAsString(categoryRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        Product product = em.find(Product.class, 1L);
        ProductFeatureRequest productFeatureRequest1 = new ProductFeatureRequest("Cor", "Branco");
        ProductFeatureRequest productFeatureRequest2 = new ProductFeatureRequest("Cor", "Branco");
        ProductFeatureRequest productFeatureRequest3 = new ProductFeatureRequest("Cor", "Branco");
        List<ProductFeatureRequest> features = new ArrayList<>();
        features.add(productFeatureRequest1);
        features.add(productFeatureRequest2);
        features.add(productFeatureRequest3);
        ProductRequest productRequest = new ProductRequest("Computador", new BigDecimal(1300), 1, features, 1L, "Produto maravilhoso");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequest))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

}