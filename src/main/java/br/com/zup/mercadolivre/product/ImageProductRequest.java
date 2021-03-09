package br.com.zup.mercadolivre.product;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ImageProductRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> images;

    public ImageProductRequest(@Size(min = 1) List<MultipartFile> images) {
        this.images = images;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "ImageProductRequest{" +
                "images=" + images +
                '}';
    }
}
