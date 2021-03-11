package br.com.zup.mercadolivre.product.detail;

import br.com.zup.mercadolivre.product.ImageProduct;

public class ProductDetailResponseImage {

    private String link;

    public ProductDetailResponseImage(ImageProduct imageProduct) {
        this.link = imageProduct.getLink();
    }

    public String getLink() {
        return link;
    }
}
