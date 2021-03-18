package br.com.zup.mercadolivre.payment;

import br.com.zup.mercadolivre.order.Order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private SystemTransactionStatus systemTransactionStatus;

    @NotBlank
    private String gatewayIdTransaction;

    private LocalDateTime createdAt;

    @ManyToOne
    @NotNull
    @Valid
    private Order order;

    @Deprecated
    public Transaction() {
    }

    public Transaction(@NotNull SystemTransactionStatus systemTransactionStatus, @NotBlank String gatewayIdTransaction, @NotNull @Valid Order order) {
        this.systemTransactionStatus = systemTransactionStatus;
        this.gatewayIdTransaction = gatewayIdTransaction;
        this.order = order;
        this.createdAt = LocalDateTime.now();
    }

    public boolean checkSuccessTransaction() {
        return this.systemTransactionStatus.equals(SystemTransactionStatus.success);

    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", systemTransactionStatus=" + systemTransactionStatus +
                ", gatewayIdTransaction='" + gatewayIdTransaction + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
