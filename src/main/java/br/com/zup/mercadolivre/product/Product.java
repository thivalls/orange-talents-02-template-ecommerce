package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.category.Category;
import br.com.zup.mercadolivre.user.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<ProductFeature> features = new HashSet<ProductFeature>();

    @NotBlank
    @Length(max = 1000)
    private String description;

    @NotNull
    @ManyToOne
    private Category categoria;

    @NotNull
    @ManyToOne
    private User owner;

    public Product(@NotBlank String name, @NotNull BigDecimal price, @NotBlank @PositiveOrZero Integer quantity, Set<ProductFeature> featuresCollection, @NotBlank @Length(max = 1000) String description, @NotNull Category categoria, @NotNull @Valid User owner) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.features = featuresCollection;
        this.description = description;
        this.categoria = categoria;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", features=" + features +
                ", description='" + description + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
