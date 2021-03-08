package br.com.zup.mercadolivre.product;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ProductFeatureRequest {

    private String name;
    private String description;

    public ProductFeatureRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProductFeature toModel(@NotNull @Valid Product product) {
        return new ProductFeature(name, description, product);
    }
}
