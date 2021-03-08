package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.user.User;
import br.com.zup.mercadolivre.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new CheckFeaturesEqualsValidator());
    }

    @PostMapping
    @Transactional
    public String store(@RequestBody @Valid ProductRequest request) {
        Optional<User> loggedUser = userRepository.findByEmail("admin@email.com");
        Product product = request.toModel(em, loggedUser.get());
        em.persist(product);
        return product.toString();
    }
}
