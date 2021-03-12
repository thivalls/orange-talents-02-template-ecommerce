package br.com.zup.mercadolivre.user;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // subir contexto do spring para rodar os testes
@AutoConfigureMockMvc // Configura o mockmvc para realizar requisições
@Transactional
@ActiveProfiles("test") // Usar este H2 em memoria não persiste os dados
// aplicar context de banco de dados para rodar o rollback
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve criar um novo usuário")
    /**
     * Lembrar de lançar o exception para eliminar os erros de compilação
     */
    void criaUsuario() throws Exception {
        UserRequest userRequest = new UserRequest("teste@teste.com", "123456");
        /**
         * Performe primeiro passo para iniciar o teste
         *      Informar o MockMvcRequestBuilder para indicar o método
         *      Informar o content em casos de post
         *      MediaType contém um ENUM com os tipos aceitos para content types
         */
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .content(objectMapper.writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deve criar um usuário com email repetido")
    void uniqueUserByEmail() throws Exception {
        UserRequest userRequest = new UserRequest("teste@teste.com", "123456");
        // poderia persistir com Entity Manager
        // ou criar utilizar o proprio MockMvc
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .content(objectMapper.writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .content(objectMapper.writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}