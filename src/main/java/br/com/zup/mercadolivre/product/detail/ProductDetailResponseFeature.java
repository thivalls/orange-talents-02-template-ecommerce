package br.com.zup.mercadolivre.product.detail;

import br.com.zup.mercadolivre.product.ProductFeature;

public class ProductDetailResponseFeature {
    private String description;
    private String name;

    public ProductDetailResponseFeature (ProductFeature productFeature) {
        this.name = productFeature.getName();
        this.description = productFeature.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
