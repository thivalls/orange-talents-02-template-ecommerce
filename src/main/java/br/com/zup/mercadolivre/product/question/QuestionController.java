package br.com.zup.mercadolivre.product.question;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.shared.email.EmailService;
import br.com.zup.mercadolivre.shared.email.FakeMailer;
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
@RequestMapping("products")
public class QuestionController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/{id}/question")
    @Transactional
    public void store(@PathVariable("id") Long id, @RequestBody @Valid QuestionRequest request) {
        Optional<User> ownerQuestion = userRepository.findByEmail("admin@email.com");
        if (!ownerQuestion.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Product product = em.find(Product.class, id);
        if (product == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Question question = request.toModel(ownerQuestion.get(), product);
        try {
            em.persist(question);
            emailService.send(
                    "Ei, " + question.getProduct().getOwner().getEmail() + " a new question has been created for product " + product.getName() + " do not forget to check it out!",
                    "Nova questão",
                    question.getOwner().getEmail(),
                    "server@mailtrap.io",
                    product.getOwner().getEmail()
            );
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
