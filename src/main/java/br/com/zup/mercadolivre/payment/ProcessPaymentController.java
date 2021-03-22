package br.com.zup.mercadolivre.payment;

import br.com.zup.mercadolivre.external.EventSuccessObserver;
import br.com.zup.mercadolivre.external.NfService;
import br.com.zup.mercadolivre.external.RankingService;
import br.com.zup.mercadolivre.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class ProcessPaymentController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    //1
    private EventSuccess event;

    @PostMapping("/return-pagseguro/{orderId}")
    @Transactional
    //1
    public String storePagseguro(@PathVariable("orderId") Long orderId, @Valid ReturnPagseguroRequest request) {
        return runTransaction(orderId, request);
    }

    @PostMapping("/return-paypal/{orderId}")
    @Transactional
    //1
    public String storePaypal(@PathVariable("orderId") Long orderId, @Valid ReturnPaypalRequest request) {
        return runTransaction(orderId, request);
    }

    //1
    private String runTransaction(Long orderId, IGatewayRequest request) {
        //1
        Order order = em.find(Order.class, orderId);
        order.addTransaction(request);
        em.merge(order);
        event.processa(order);
        return order.toString();
    }
}
