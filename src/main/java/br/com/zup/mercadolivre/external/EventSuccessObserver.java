package br.com.zup.mercadolivre.external;

import br.com.zup.mercadolivre.order.Order;

/**
 * Todo processo de sucesso deve implementar esta interface
 */
public interface EventSuccessObserver {
    void processa(Order order);
}
