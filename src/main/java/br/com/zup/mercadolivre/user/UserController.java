package br.com.zup.mercadolivre.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public void store(@Valid @RequestBody UserRequest request) {
        User user = request.toModel(request.getEmail(), request.getPassword());
        em.persist(user);
    }

}
