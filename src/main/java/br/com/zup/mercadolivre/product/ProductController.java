package br.com.zup.mercadolivre.product;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("products")
public class ProductController {

    @PostMapping
    public String store(@RequestBody @Valid ProductRequest request) {
        Product product = request.toModel();
        return "cheguei ate aqui";
    }

}
