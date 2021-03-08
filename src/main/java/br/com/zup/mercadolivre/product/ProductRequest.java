package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.category.Category;
import br.com.zup.mercadolivre.shared.validations.ExistField;
import br.com.zup.mercadolivre.user.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductRequest {

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    @Positive
    private int quantity;

    @Size(min = 3)
    private Set<ProductFeature> features = new HashSet<>();

    @NotNull
    @ExistField(domainClass = Category.class, fieldName = "id", message = "This category does not exist")
    private Long categoria;

    @NotBlank
    @Length(max = 1000)
    private String description;

    @NotNull
    @Valid
    private User owner;

    public ProductRequest(@NotBlank String name, @NotNull @PositiveOrZero BigDecimal price, @PositiveOrZero Integer quantity, @Size(min = 3) Set<ProductFeature> features, @NotNull Long categoria, @NotBlank @Length(max = 1000) String description, @NotNull @Valid User owner) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.features = features;
        this.categoria = categoria;
        this.description = description;
        this.owner = owner;
    }

    public Product toModel(EntityManager em, User loggedUser) {
        Category category = em.find(Category.class, categoria);
        return new Product(name, price, quantity, features, description, category, owner);
    }
}
