package br.com.zup.mercadolivre.product.opinion;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;
import br.com.zup.mercadolivre.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class OpinionController {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{id}/opinion")
    @Transactional
    public String store(@PathVariable("id") Long id, @RequestBody @Valid OpinionRequest request) {
        Optional<User> loggedUser = userRepository.findByEmail("admin1@email.com");
        if(!loggedUser.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Product product = em.find(Product.class, id);
        if(product == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Opinion opinion = request.toModel(em, loggedUser.get(), product);
        em.persist(opinion);
    }

}
