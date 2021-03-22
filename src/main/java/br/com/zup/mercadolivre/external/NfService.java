package br.com.zup.mercadolivre.external;

import br.com.zup.mercadolivre.order.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public class NfService implements EventSuccessObserver {
    @Override
    public void processa(Order order) {
        Assert.isTrue(order.processedWithSuccess(), "Não deveria executar este método sem um sucesso na compra" + order);

        RestTemplate restTemplate = new RestTemplate();
        SystemReportNfRequest systemReportNfRequest = new SystemReportNfRequest(order.getId(), order.getBuyer().getId());

        /**
         * Uma segunda forma de enviar um objeto para request abaixo seria utilizar um Map
         */
        // Map<String, Object> request = Map.of("orderId", order.getId(), "ownerId", order.getBuyer().getId());

        restTemplate.postForEntity("http://localhost:8080/external/services/nf", systemReportNfRequest, String.class);
    }
}
