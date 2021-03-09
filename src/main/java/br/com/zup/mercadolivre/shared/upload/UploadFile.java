package br.com.zup.mercadolivre.shared.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploadFile {
    public Set<String> upload(List<MultipartFile> files) {
        return files.stream().map(image -> image.getOriginalFilename()).collect(Collectors.toSet());
    }
}
