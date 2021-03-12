package br.com.zup.mercadolivre.user;

import br.com.zup.mercadolivre.shared.validations.UniqueField;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {
    @NotBlank
    @Email
    @UniqueField(domainClass = User.class, fieldName = "email", message = "This email already exist")
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

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

    public User toModel(String email, String password) {
        return new User(email, BCrypt.hashpw(password, BCrypt.gensalt()));
    }
}
