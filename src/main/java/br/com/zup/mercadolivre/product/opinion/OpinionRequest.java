package br.com.zup.mercadolivre.product.opinion;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class OpinionRequest {
    @Positive
    @Max(value = 5)
    private int review;

    @NotBlank
    private String title;

    @NotBlank
    @Length(max = 500)
    private String description;

    public OpinionRequest(@Positive Integer review, @NotBlank String title, @NotBlank @Length(max = 500) String description) {
        this.review = review;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "OpinionRequest{" +
                "review=" + review +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Opinion toModel(User loggedUser, Product product) {
        return new Opinion(review, title, description, loggedUser, product);
    }
}
