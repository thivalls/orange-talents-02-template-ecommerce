package br.com.zup.mercadolivre.order;

import br.com.zup.mercadolivre.user.User;
import br.com.zup.mercadolivre.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("orders")
public class OrderController {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public String store(@RequestBody @Valid OrderRequest request) {
        Optional<User> user = userRepository.findByEmail("email@email.com");
        if(!user.isPresent()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged in");

        Order order = request.toModel(em, user.get());
        em.persist(order);
        return order.toString();
    }

}
