package br.com.zup.mercadolivre.order;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.shared.email.EmailService;
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

    @Autowired
    private EmailService emailService;

    @PostMapping
    @Transactional
    public String store(@RequestBody @Valid OrderRequest request) {
        Optional<User> user = userRepository.findByEmail("email@email.com");
        if(!user.isPresent()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged in");

        Product product = em.find(Product.class, request.getProductId());
        if(product == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");

        Order order = request.toModel(product, user.get());

        Boolean stockDebit = product.applyStockDebit(request.getQuantity());

        if(stockDebit) {
            try {
                // em.merge(product);
                em.persist(order);
                emailService.send(
                        "Ei, " + product.getOwner().getEmail() + " a new order has been created for product " + product.getName() + " do not forget to check it out!",
                        "Nova ordem de compra",
                        product.getOwner().getEmail(),
                        "server@mailtrap.io",
                        product.getOwner().getEmail()
                );
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return order.toString();
    }

}
