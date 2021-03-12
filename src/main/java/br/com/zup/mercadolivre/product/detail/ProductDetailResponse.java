package br.com.zup.mercadolivre.product.detail;

import br.com.zup.mercadolivre.product.Product;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;

public class ProductDetailResponse {
    private String name;
    private BigDecimal price;
    private String description;
    private Set<ProductDetailResponseFeature> features;

    private Set<String> images;
    private SortedSet<String> questions;
    private Set<Map<String, String>> opnions;

    private Set<Integer> reviews;
    private double averageReviews;

    public ProductDetailResponse(@NotNull Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.features = product.collectFeatures(ProductDetailResponseFeature::new);
        this.images = product.collectImages(image -> image.getLink());
        this.questions = product.collectQuestions(question -> question.getTitle());
        this.opnions = product.collectOpnions(opinion -> {
            return Map.of("title", opinion.getTitle(), "description", opinion.getDescription());
        });
        this.reviews = product.collectOpnions(opinion -> opinion.getReview());
        OptionalDouble averageReviews = reviews.stream().mapToInt(review -> review).average();
        this.averageReviews = averageReviews.orElseGet(() -> 0.0);
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

    public SortedSet<String> getQuestions() {
        return questions;
    }

    public Set<Map<String, String>> getOpnions() {
        return opnions;
    }

    public int getReviews() {
        return reviews.size();
    }

    public double getAverageReviews() {
        return averageReviews;
    }
}
