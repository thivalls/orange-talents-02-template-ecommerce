package br.com.zup.mercadolivre.order;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.shared.validations.ExistField;
import br.com.zup.mercadolivre.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OrderRequest {

    @NotBlank
    private String paymentGateway;

    @NotNull
    @ExistField(domainClass = Product.class, fieldName = "id", message = "Product not found")
    private Long productId;

    @Positive
    @NotNull
    private int quantity;

    public OrderRequest(String paymentGateway, @NotNull Long productId, @Positive @NotNull int quantity) {
        this.paymentGateway = paymentGateway;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Order toModel(Product product, User buyer) {
        return new Order(paymentGateway, product, quantity, buyer);
    }

    public String getPaymentGateway() {
        return paymentGateway;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
