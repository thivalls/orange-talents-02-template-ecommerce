package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.category.Category;
import br.com.zup.mercadolivre.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ProductTest {
    @Test
    @DisplayName("It should debit stock when stock is greater than the quantity in normal condition")
    void itShouldDebitStockInNormalCondition() {
        User owner = new User("email@email.com", "$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG");
        List<ProductFeatureRequest> features = List.of(
                new ProductFeatureRequest("cor", "amarela"),
                new ProductFeatureRequest("tamanho", "2m"),
                new ProductFeatureRequest("voltagem", "110V")
        );
        Category category = new Category("Esporte", null);
        Product product = new Product("Product test", new BigDecimal(100), 100, "product description", category, owner, features);
        product.applyStockDebit(10);

        Assertions.assertEquals(90, product.getQuantity());
    }

    @ParameterizedTest
    @CsvSource({"100", "99", "1"})
    @DisplayName("It should debit stock if quantity is less than stock of product or equal")
    void itShouldDebitStockBoundaryTest(int quantity) {
        User owner = new User("email@email.com", "$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG");
        List<ProductFeatureRequest> features = List.of(
                new ProductFeatureRequest("cor", "amarela"),
                new ProductFeatureRequest("tamanho", "2m"),
                new ProductFeatureRequest("voltagem", "110V")
        );
        Category category = new Category("Esporte", null);
        Product product = new Product("Product test", new BigDecimal(100), 100, "product description", category, owner, features);

        Assertions.assertTrue(() -> product.applyStockDebit(quantity));
    }

    @ParameterizedTest
    @CsvSource({"101", "1000"})
    @DisplayName("It should not debit stock when quantity is greater than the real stock of product")
    void itShouldNotDebitStockWhenDebitQuantityIsGreaterThanStock(int quantity) {
        User owner = new User("email@email.com", "$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG");
        List<ProductFeatureRequest> features = List.of(
                new ProductFeatureRequest("cor", "amarela"),
                new ProductFeatureRequest("tamanho", "2m"),
                new ProductFeatureRequest("voltagem", "110V")
        );
        Category category = new Category("Esporte", null);
        Product product = new Product("Product test", new BigDecimal(100), 100, "product description", category, owner, features);

        Assertions.assertFalse(() -> product.applyStockDebit(quantity));
    }

    @ParameterizedTest
    @CsvSource({"0", "-1", "-100"})
    @DisplayName("It should not debit stock when stock is less than zero or equal zero")
    void itShouldNotDebitStockWhenQuantityIsZeroOrLessThanZero(int quantity) throws Exception {
        User owner = new User("email@email.com", "$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG");
        List<ProductFeatureRequest> features = List.of(
                new ProductFeatureRequest("cor", "amarela"),
                new ProductFeatureRequest("tamanho", "2m"),
                new ProductFeatureRequest("voltagem", "110V")
        );
        Category category = new Category("Esporte", null);
        Product product = new Product("Product test", new BigDecimal(100), 100, "product description", category, owner, features);

        Assertions.assertThrows(IllegalArgumentException.class, () -> product.applyStockDebit(quantity));
    }
}