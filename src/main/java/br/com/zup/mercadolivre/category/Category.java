package br.com.zup.mercadolivre.category;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @ManyToOne
    private Category category;

    @Deprecated
    public Category() {
    }

    public Category(@NotBlank String name) {
        this.name = name;
    }

    public Category(@NotBlank String name, Category category) {
        Assert.notNull(name, "The name of category can not be null");
        if (category != null) {
            Assert.isInstanceOf(Category.class, category, "The category object must be an instance of Category Entity");
        }
        this.name = name;
        this.category = category;
    }
}
