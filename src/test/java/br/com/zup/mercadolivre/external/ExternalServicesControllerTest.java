package br.com.zup.mercadolivre.external;

import br.com.zup.mercadolivre.user.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ExternalServicesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @PersistenceContext
    private EntityManager em;

    @Test
    void mustCreateNewRegisterInNfSystem() throws Exception{
        UserRequest userRequest = new UserRequest("teste@teste.com", "123456");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .content(objectMapper.writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        );

//        OrderRequest orderRequest = new OrderRequest("teste@teste.com", "123456");
//
//        Product product = em.find(Product.class, request.getProductId());
//        if(product == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
//
//        Order order = request.toModel(request.getPaymentGateway(), product, user.get());
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/users")
//                        .content(objectMapper.writeValueAsString(userRequest))
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        SystemReportRequest request = new SystemReportRequest(1L, 1L);
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/system/nf")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request))
//        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}