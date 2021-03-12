package br.com.zup.mercadolivre.product.detail;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.product.opinion.Opinion;
import br.com.zup.mercadolivre.user.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ProductDetailResponseOpinion {

    private int review;
    private String title;
    private String description;

    public ProductDetailResponseOpinion(@Positive Integer review, @NotBlank String title, @NotBlank @Length(max = 500) String description) {
        this.review = review;
        this.title = title;
        this.description = description;
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
}
