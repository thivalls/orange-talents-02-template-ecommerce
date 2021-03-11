package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.shared.upload.UploadFile;
import br.com.zup.mercadolivre.user.User;
import br.com.zup.mercadolivre.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("products")
public class ProductController {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UploadFile uploadFile;

    @InitBinder(value = "ProductRequest")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new CheckFeaturesEqualsValidator());
    }

    @PostMapping
    @Transactional
    public String store(@RequestBody @Valid ProductRequest request) {
        Optional<User> loggedUser = userRepository.findByEmail("admin1@email.com");
        if (!loggedUser.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Product product = request.toModel(em, loggedUser.get());
        em.persist(product);
        return product.toString();
    }

    @PostMapping("/{id}/images")
    @Transactional
    public String storeImage(@PathVariable("id") Long id, @Valid ImageProductRequest request) {
        /**
         * 1) Enviar imagens para o local correto
         * 2) Pegar os links de todas as imagens
         * 3) Associar os links ao produto
         * 4) Carregar o produto com as imagens
         * 5) Atualizar o produto com as imagens
         */

        Optional<User> loggedUser = userRepository.findByEmail("admin@email.com");
        Product product = em.find(Product.class, id);

        Set<String> imageLinks = uploadFile.upload(request.getImages());
        product.appendImages(imageLinks);
        if (!product.checkOwner(loggedUser.get())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        em.merge(product);
        return product.toString();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ProductDetail show(@PathVariable("id") Long id) {
        Product product = em.find(Product.class, id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ProductDetail productDetail = new ProductDetail(product);
        return productDetail;
    }
}
