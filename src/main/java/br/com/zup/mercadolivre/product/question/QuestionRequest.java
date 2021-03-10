package br.com.zup.mercadolivre.product.question;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;

import javax.validation.constraints.NotBlank;

public class QuestionRequest {

    @NotBlank
    private String title;

    @Deprecated
    public QuestionRequest() {
    }

    public QuestionRequest(@NotBlank String title) {
        this.title = title;
    }

    public Question toModel(User owner, Product product) {
        return new Question(title, owner, product);
    }

    public String getTitle() {
        return title;
    }
}
