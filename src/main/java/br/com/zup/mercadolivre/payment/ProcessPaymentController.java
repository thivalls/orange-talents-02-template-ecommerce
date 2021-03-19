package br.com.zup.mercadolivre.payment;

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

@RestController
public class ProcessPaymentController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private NfService nfService;

    @Autowired
    private RankingService rankingService;

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
        if(order.processedWithSuccess()) {
            // falar com nota fiscal
            nfService.processa(order);
            // falar com ranking
            rankingService.processa(order);
            // mandar email para quem comprou
            // emailTransaction.processa(order);
        }
        return order.toString();
    }
}
