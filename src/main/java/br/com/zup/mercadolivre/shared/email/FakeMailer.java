package br.com.zup.mercadolivre.shared.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeMailer implements Mailer {
    @Override
    public void send(String body, String subject, String fromName, String from, String to) {
        System.out.println(body);
        System.out.println(subject);
        System.out.println(fromName);
        System.out.println(from);
        System.out.println(to);
    }
}
