package br.com.zup.mercadolivre.external;

import br.com.zup.mercadolivre.order.Order;
import br.com.zup.mercadolivre.user.User;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

public class SystemReportNfRequest {
    @NotNull
    private Long orderId;

    @NotNull
    private Long ownerId;

    public SystemReportNfRequest(@NotNull Long orderId, @NotNull Long ownerId) {
        this.orderId = orderId;
        this.ownerId = ownerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    @Override
    public String toString() {
        return "SystemNfRequest{" +
                "orderId=" + orderId +
                ", owner=" + ownerId +
                '}';
    }

    public Nf toModel(EntityManager em) {
        Order order = em.find(Order.class, orderId);
        User owner = em.find(User.class, ownerId);
        Assert.isTrue(order != null, "Order not found");
        Assert.isTrue(owner != null, "User/Owner not found");
        return new Nf(order, owner);
    }
}
