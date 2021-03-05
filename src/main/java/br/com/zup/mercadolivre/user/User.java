package br.com.zup.mercadolivre.user;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Deprecated
    public User() {
    }

    public User(@NotBlank @Email String email, @NotBlank String password) {
        Assert.notNull(email, "The field email can not be null");
        Assert.notNull(password, "The field password can not be null");
        Assert.isTrue(password.length() >= 6, "The field password must be greater than 6");

        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
