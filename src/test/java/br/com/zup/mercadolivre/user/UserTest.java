package br.com.zup.mercadolivre.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    @DisplayName("Deveria criar um usuário sem erros")
    void deveriaCriarUsuario() {
        Assertions.assertDoesNotThrow(() -> new User("fulano@email.com", "123456"));
    }

    @Test
    @DisplayName("Deveria retornar erro ao passar email nulo")
    void deveriaRetornarErroAoTentarCriarUsuarioComEmailNulo() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(null, "123456"));
    }

    @Test
    @DisplayName("Não deveria permitir password menores que 6 caracteres")
    void naoDeveriaPermitirPasswordMenorQue6Caracteres() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User("fulano@email.come", "12345"));
    }
}