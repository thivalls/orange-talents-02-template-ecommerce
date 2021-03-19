package br.com.zup.mercadolivre.external;

import br.com.zup.mercadolivre.order.Order;
import br.com.zup.mercadolivre.user.User;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

public class SystemReportRankingRequest {
    @NotNull
    private Long orderId;

    @NotNull
    private Long sellerId;

    public SystemReportRankingRequest(@NotNull Long orderId, @NotNull Long sellerId) {
        this.orderId = orderId;
        this.sellerId = sellerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    @Override
    public String toString() {
        return "SystemNfRequest{" +
                "orderId=" + orderId +
                ", owner=" + sellerId +
                '}';
    }

    public Ranking toModel(EntityManager em) {
        Order order = em.find(Order.class, orderId);
        User owner = em.find(User.class, sellerId);
        Assert.isTrue(order != null, "Order not found");
        Assert.isTrue(owner != null, "User/Owner not found");
        return new Ranking(order, owner);
    }
}
