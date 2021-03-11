package br.com.zup.mercadolivre.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "product_feature")
public class ProductFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @NotNull
    @ManyToOne
    private Product product;

    @Deprecated
    public ProductFeature() {
    }

    public ProductFeature(@NotBlank String name, @NotBlank String description, @NotNull @Valid Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ProductFeature{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductFeature)) return false;
        ProductFeature that = (ProductFeature) o;
        return id.equals(that.id) && name.equals(that.name) && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, product);
    }
}
