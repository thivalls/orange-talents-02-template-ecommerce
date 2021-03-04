package br.com.zup.mercadolivre.user;

import br.com.zup.mercadolivre.shared.validations.NotFuture;
import br.com.zup.mercadolivre.shared.validations.UniqueField;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserRequest {
    @NotBlank
    @Email
    @UniqueField(domainClass = User.class, fieldName = "email", message = "This email already exist")
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    // @NotNull
    // @NotFuture(domainClass = User.class, fieldName = "created_at", message = "The date can not be in the future")
    // private LocalDateTime created_at;

    public UserRequest(@NotBlank @Email String email, @NotBlank @Size String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

//    public LocalDateTime getCreated_at() {
//        return created_at;
//    }

    public User toModel(String email, String password) {
        return new User(email, password);
    }
}
