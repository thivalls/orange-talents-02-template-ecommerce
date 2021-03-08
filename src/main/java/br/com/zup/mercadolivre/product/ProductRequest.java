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
    private List<ProductFeatureRequest> features = new ArrayList<>();

    @NotNull
    @ExistField(domainClass = Category.class, fieldName = "id", message = "This category does not exist")
    private Long categoria;

    @NotBlank
    @Length(max = 1000)
    private String description;

    public ProductRequest(@NotBlank String name, @NotNull @Positive BigDecimal price, @Positive int quantity, @Size(min = 3) List<ProductFeatureRequest> features, @NotNull Long categoria, @NotBlank @Length(max = 1000) String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.features = features;
        this.categoria = categoria;
        this.description = description;
    }

    public Product toModel(EntityManager em, User owner) {
        Category category = em.find(Category.class, categoria);
        return new Product(name, price, quantity, description, category, owner, features);
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", features=" + features +
                ", categoria=" + categoria +
                ", description='" + description + '\'' +
                '}';
    }

    public Set<String> searchEqualsFeatures() {
        HashSet<String> testAddingEqualsName = new HashSet<>();
        HashSet<String> equalsResults = new HashSet<>();
        for (ProductFeatureRequest feature : features) {
            String featureName = feature.getName();
            if (!testAddingEqualsName.add(featureName)) {
                equalsResults.add(featureName);
            }
        }
        return equalsResults;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<ProductFeatureRequest> getFeatures() {
        return features;
    }

    public Long getCategoria() {
        return categoria;
    }
}
