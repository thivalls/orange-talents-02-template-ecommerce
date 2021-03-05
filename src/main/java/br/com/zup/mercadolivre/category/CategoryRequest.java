package br.com.zup.mercadolivre.category;

import br.com.zup.mercadolivre.shared.validations.ExistFieldOrNull;
import br.com.zup.mercadolivre.shared.validations.UniqueField;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CategoryRequest {
    @NotBlank
    @UniqueField(domainClass = Category.class, fieldName = "name", message = "This category has already been created")
    private String name;

    @ExistFieldOrNull(domainClass = Category.class, fieldName = "id", message = "This category does not exist")
    private Long categoryId;

    public CategoryRequest(@NotBlank String name, Long categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public Category toModel(EntityManager em) {
        if (categoryId != null) {
            Category parent = em.find(Category.class, categoryId);
            return new Category(name, parent);
        }

        return new Category(name);
    }

    public String getName() {
        return name;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
