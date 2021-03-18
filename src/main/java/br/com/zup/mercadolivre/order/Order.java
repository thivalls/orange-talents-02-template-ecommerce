package br.com.zup.mercadolivre.order;

import br.com.zup.mercadolivre.payment.ReturnPagseguroRequest;
import br.com.zup.mercadolivre.payment.Transaction;
import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String status;
    @Enumerated
    @NotNull
    private Gateway paymentGateway;

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

    @OneToMany(mappedBy = "order", cascade = CascadeType.MERGE)
    private List<Transaction> transactions;

    @Deprecated
    public Order() {
    }
    
    public Order(@NotNull Gateway paymentGateway, @NotNull Product product, @Positive @NotNull int quantity, @NotNull User buyer) {
        this.status = OrderStatus.STARTED.toString();
        this.paymentGateway = paymentGateway;
        this.product = product;
        this.quantity = quantity;
        this.buyer = buyer;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Gateway getPaymentGateway() {
        return paymentGateway;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public User getBuyer() {
        return buyer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", paymentGateway=" + paymentGateway +
                ", product=" + product +
                ", quantity=" + quantity +
                ", buyer=" + buyer +
                ", transactions=" + transactions +
                '}';
    }

    public void addTransaction(@Valid ReturnPagseguroRequest request) {
        Transaction transaction = request.toTransaction(this);
        Assert.isTrue(!this.transactions.contains(transaction), "This transaction already been added");
        Set<Transaction> successTransaction = this.transactions.stream().filter(Transaction::checkSuccessTransaction).collect(Collectors.toSet());
        Assert.isTrue(successTransaction.isEmpty(), "This transaction already been finished with success");
        this.transactions.add(request.toTransaction(this));
    }
}
