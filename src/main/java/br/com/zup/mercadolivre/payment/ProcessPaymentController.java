package br.com.zup.mercadolivre.payment;

import br.com.zup.mercadolivre.order.Order;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ProcessPaymentController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping("/return-pagseguro/{orderId}")
    @Transactional
    public String storePagseguro(@PathVariable("orderId") Long orderId, @Valid ReturnPagseguroRequest request) {
        return runTransaction(orderId, request);
    }

    @PostMapping("/return-paypal/{orderId}")
    @Transactional
    public String storePaypal(@PathVariable("orderId") Long orderId, @Valid ReturnPaypalRequest request) {
        return runTransaction(orderId, request);
    }

    private String runTransaction(Long orderId, IGatewayRequest request) {
        Order order = em.find(Order.class, orderId);
        Assert.notNull(order, "Order not found");
        order.addTransaction(request);
        em.merge(order);
        return order.toString();
    }
}
