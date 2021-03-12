package br.com.zup.mercadolivre.category;

import br.com.zup.mercadolivre.product.opinion.Opinion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryTest {
    @Test
    @DisplayName("It should create a category without parent category")
    void mustCreateCategoryWithoutParent() {
        Assertions.assertDoesNotThrow(() -> new Category("Esporte", null));
    }

    @Test
    @DisplayName("It should create a category with parent category")
    void mustCreateCategoryWithParent() {
        Assertions.assertDoesNotThrow(() -> new Category("Esporte", new Category("Esporte", null)));
    }

    @Test
    @DisplayName("It should not create a category with null name")
    void mustNotCreateCategoryWithNullName() {
        Assertions.assertThrows(IllegalStateException.class, () -> new Category(null, null));
    }

    @Test
    @DisplayName("It should not create a category with blank name")
    void mustNotCreateCategoryWithBlankName() {
        Assertions.assertThrows(IllegalStateException.class, () -> new Category("", null));
    }
}