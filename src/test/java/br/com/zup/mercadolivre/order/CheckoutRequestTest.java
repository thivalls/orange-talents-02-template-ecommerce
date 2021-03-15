package br.com.zup.mercadolivre.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
class CheckoutRequestTest {
    @Autowired
    private EntityManager em;


}