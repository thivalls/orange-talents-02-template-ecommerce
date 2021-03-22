package br.com.zup.mercadolivre.payment;

import br.com.zup.mercadolivre.external.EventSuccessObserver;
import br.com.zup.mercadolivre.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventSuccess {
    @Autowired
    //1
    private Set<EventSuccessObserver> events;


    public void processa(Order order) {
        //1
        if(order.processedWithSuccess()) {
            //1
            events.forEach(event -> event.processa(order));
            // mandar email para quem comprou
            // emailTransaction.processa(order);
        } else {
            // evento de falha
        }
    }
}
