package br.com.zup.mercadolivre.product.question;


import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.shared.validations.ExistField;
import br.com.zup.mercadolivre.user.User;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_questions")
public class Question implements Comparable<Question>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private User owner;

    @NotNull
    @ManyToOne
    private Product product;

    @Deprecated
    public Question() {
    }

    public Question(@NotBlank String title, @NotNull User owner, @NotNull Product product) {
        Assert.isTrue(title.length() > 0, "The title can not be empty");
        Assert.isTrue(owner != null, "The user is required and can not be null");
        Assert.isTrue(product != null, "The product is required and can not be null");
        this.title = title;
        this.owner = owner;
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", owner=" + owner +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (!getTitle().equals(question.getTitle())) return false;
        if (!getOwner().equals(question.getOwner())) return false;
        return getProduct().equals(question.getProduct());
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + getOwner().hashCode();
        result = 31 * result + getProduct().hashCode();
        return result;
    }

    public User getOwner() {
        return owner;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public int compareTo(Question o) {
        return this.title.compareTo(o.title);
    }
}
