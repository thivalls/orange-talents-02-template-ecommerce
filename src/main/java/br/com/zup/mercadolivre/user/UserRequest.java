package br.com.zup.mercadolivre.user;

import br.com.zup.mercadolivre.shared.validations.NotFuture;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserRequest {
    @NotBlank
    @Email
//    @UniqueField(domainClass = User.class, fieldName = "email")
    private String email;

    @NotBlank
    private String password;

    @NotNull
    @NotFuture(domainClass = User.class, fieldName = "created_at", message = "The date can not be in the future")
    private LocalDateTime created_at;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public UserRequest(@NotBlank @Email String email, @NotBlank String password, @NotNull LocalDateTime created_at) {
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }
}
