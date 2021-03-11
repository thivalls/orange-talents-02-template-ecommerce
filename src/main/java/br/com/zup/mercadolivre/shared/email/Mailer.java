package br.com.zup.mercadolivre.shared.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {
    /**
     * @param body corpo do email
     * @param subject assunto do email
     * @param fromName nome dem quem está enviando
     * @param from email de envio
     * @param to email de destino
     */
    void send(@NotBlank String body, @NotBlank String subject, @NotBlank String fromName, @NotBlank @Email String from, @NotBlank @Email String to);
}
