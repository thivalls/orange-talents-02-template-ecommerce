package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.product.opinion.Opinion;
import br.com.zup.mercadolivre.product.question.Question;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class ProductDetail {
    private Set<ImageProduct> images;
    private String name;
    private BigDecimal price;
    private Set<ProductFeature> features;
    private String description;
    private int totalReviews;
    private double averageReviews;

    private List<Opinion> opnions;
    private List<Question> questions;

    public ProductDetail(Product product) {
        this.images = product.getImages();
        this.name = product.getName();
        this.price = product.getPrice();
        this.features = product.getFeatures();
        this.description = product.getDescription();
        this.opnions = product.getOpinions();
        this.questions = product.getQuestions();
        this.totalReviews = opnions.size();
        this.averageReviews = getAverageReviews();
    }

    public Set<ImageProduct> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Set<ProductFeature> getFeatures() {
        return features;
    }

    public String getDescription() {
        return description;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public List<Opinion> getOpnions() {
        return opnions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "{\"ProductDetail\":{"
                + ", \"images\":" + images
                + ", \"name\":\"" + name + "\""
                + ", \"price\":\"" + price + "\""
                + ", \"features\":" + features
                + ", \"description\":\"" + description + "\""
                + ", \"totalReviews\":\"" + totalReviews + "\""
                + ", \"averageReviews\":\"" + averageReviews + "\""
                + ", \"opnions\":" + opnions
                + ", \"questions\":" + questions
                + "}}";
    }

    private double getAverageReviews() {
        double average = 0.0;
        for (Opinion opinion : opnions) {
            average += opinion.getReview();
        }
        return this.averageReviews = average / totalReviews;
    }

}
