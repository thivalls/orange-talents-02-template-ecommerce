package br.com.zup.mercadolivre.payment;

import br.com.zup.mercadolivre.order.Order;

public interface IGatewayRequest {
    public Transaction toTransaction(Order order);
}
