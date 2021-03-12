package br.com.zup.mercadolivre.product.opinion;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "product_opinions")
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Max(value = 5)
    private int review;

    @NotBlank
    private String title;

    @NotBlank
    @Length(max = 500)
    private String description;

    @NotNull
    @ManyToOne
    private User owner;

    @NotNull
    @ManyToOne
    private Product product;

    @Deprecated
    public Opinion() {
    }

    public Opinion(@Positive @Max(value = 5) int review, @NotBlank String title, @NotBlank @Length(max = 500) String description, @NotNull User owner, @NotNull Product product) {
        this.review = review;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.product = product;
    }

    public int getReview() {
        return review;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getOwner() {
        return owner;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "id=" + id +
                ", review=" + review +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", product=" + product +
                '}';
    }
}
