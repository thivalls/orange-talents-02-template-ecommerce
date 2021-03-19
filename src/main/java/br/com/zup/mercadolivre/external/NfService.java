package br.com.zup.mercadolivre.external;

import br.com.zup.mercadolivre.order.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NfService {

    public void processa(Order order) {
        RestTemplate restTemplate = new RestTemplate();
        SystemReportNfRequest systemReportNfRequest = new SystemReportNfRequest(order.getId(), order.getBuyer().getId());

        /**
         * Uma segunda forma de enviar um objeto para request abaixo seria utilizar um Map
         */
        // Map<String, Object> request = Map.of("orderId", order.getId(), "ownerId", order.getBuyer().getId());

        restTemplate.postForEntity("http://localhost:8080/external/services/nf", systemReportNfRequest, String.class);
    }
}
