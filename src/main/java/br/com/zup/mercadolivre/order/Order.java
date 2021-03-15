package br.com.zup.mercadolivre.order;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String status;
    @NotBlank
    private String paymentGateway;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Positive
    @NotNull
    private int quantity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    public Order(@NotBlank String paymentGateway, @NotNull Product product, @Positive @NotNull int quantity, @NotNull User buyer) {
        this.status = OrderStatus.STARTED.toString();
        this.paymentGateway = paymentGateway;
        this.product = product;
        this.quantity = quantity;
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "{\"Order\":{"
                + "\"id\":\"" + id + "\""
                + ", \"status\":" + status
                + ", \"paymentGateway\":" + paymentGateway
                + ", \"product\":" + product
                + ", \"quantity\":\"" + quantity + "\""
                + ", \"buyer\":" + buyer
                + "}}";
    }
}
