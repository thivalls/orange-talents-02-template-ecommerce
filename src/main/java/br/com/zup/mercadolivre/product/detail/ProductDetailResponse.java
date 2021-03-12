package br.com.zup.mercadolivre.product.detail;

import br.com.zup.mercadolivre.product.Product;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class ProductDetailResponse {
    private String name;
    private BigDecimal price;
    private String description;
    private Set<ProductDetailResponseFeature> features;

    private Set<String> images;
    private Set<ProductDetailResponseOpinion> opinions;
//    private int totalReviews;
//    private double averageReviews;
//
//    private List<Opinion> opnions;
//    private List<Question> questions;

    public ProductDetailResponse(@NotNull Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.features = product.collectFeatures(ProductDetailResponseFeature::new);
        this.images = product.collectImages(image -> image.getLink());
//        this.opinios
//        this.opnions = product.getOpinions();
//        this.questions = product.getQuestions();
//        this.totalReviews = opnions.size();
//        this.averageReviews = getAverageReviews();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Set<ProductDetailResponseFeature> getFeatures() {
        return features;
    }

    public Set<String> getImages() {
        return images;
    }

    //    public int getTotalReviews() {
//        return totalReviews;
//    }

//    public List<Opinion> getOpnions() {
//        return opnions;
//    }
//
//    public List<Question> getQuestions() {
//        return questions;
//    }

//    private double getAverageReviews() {
//        double average = 0.0;
//        for (Opinion opinion : opnions) {
//            average += opinion.getReview();
//        }
//        return this.averageReviews = average / totalReviews;
//    }

}
