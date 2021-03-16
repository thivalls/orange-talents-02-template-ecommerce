package br.com.zup.mercadolivre.payment;

import br.com.zup.mercadolivre.order.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class ProcessPaymentController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping("/return-pagseguro/{orderId}")
    public String storePagseguro(@PathVariable("orderId") Long orderId, @Valid ReturnPagseguroRequest request) {
        Order order = em.find(Order.class, orderId);
        order.tryPayment(request);
        return request.toString() + orderId;
    }
}
