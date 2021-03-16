package br.com.zup.mercadolivre.order;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.shared.email.EmailService;
import br.com.zup.mercadolivre.user.User;
import br.com.zup.mercadolivre.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

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
    public String store(@RequestBody @Valid OrderRequest request, UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Optional<User> user = userRepository.findByEmail("email@email.com");
        if(!user.isPresent()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged in");

        Product product = em.find(Product.class, request.getProductId());
        if(product == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");

        Order order = request.toModel(request.getPaymentGateway(), product, user.get());

        Boolean stockDebit = product.applyStockDebit(request.getQuantity());

        if(stockDebit) {
//            try {
                // em.merge(product); o hibernate jpa já faz a sincronização da alteração do produto, não preciso dar o merge senão a ação será feita duas vezes
                em.persist(order);
                emailService.send(
                        "Ei, " + product.getOwner().getEmail() + " a new order has been created for product " + product.getName() + " do not forget to check it out!",
                        "Nova ordem de compra",
                        product.getOwner().getEmail(),
                        "server@mailtrap.io",
                        product.getOwner().getEmail()
                );
                if(request.getPaymentGateway().equals(Gateway.PAGSEGURO)) {
                    String urlPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(order.getId()).toString();
                    return "pagseguro.com/" + order.getId() + "?redirectUrl=" + urlPagseguro;
                }else {
                    String urlPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(order.getId()).toString();
                    return "paypal.com/" + order.getId() + "?redirectUrl=" + urlPaypal;
                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
        }

        BindException orderRequestStockError = new BindException(request, "orderRequest");
        orderRequestStockError.reject(null, "There is not enough stock");
        throw orderRequestStockError;
    }

}
