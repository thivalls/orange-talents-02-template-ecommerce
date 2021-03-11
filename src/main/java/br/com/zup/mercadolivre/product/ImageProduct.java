package br.com.zup.mercadolivre.product;

import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_images")
public class ImageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Product product;

    @NotBlank
//    @URL
    private String link;

    @Deprecated
    public ImageProduct() {
    }

    public ImageProduct(@NotNull @Valid Product product, @NotBlank /** @URL */ String link) {
        this.link = link;
        this.product = product;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "ImageProduct{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }
}
