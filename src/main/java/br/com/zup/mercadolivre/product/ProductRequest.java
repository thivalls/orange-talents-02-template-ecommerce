package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.category.Category;
import br.com.zup.mercadolivre.shared.validations.ExistField;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class ProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    @PositiveOrZero
    private BigDecimal price;

    @PositiveOrZero
    private Integer quantity;

    @NotBlank
    @Size(min = 3)
    private List<ProductFeature> features;

    @NotNull
    @ExistField(domainClass = Category.class, fieldName = "id", message = "This category does not exist")
    private Category categoria;

    @NotBlank
    @Size(max = 1000)
    private String description;

    public Product toModel() {
        return new Product();
    }
}
