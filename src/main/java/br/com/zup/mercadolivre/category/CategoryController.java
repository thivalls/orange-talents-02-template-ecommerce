package br.com.zup.mercadolivre.category;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public void store(@RequestBody @Valid CategoryRequest request) {
        Category category = request.toModel(em);
        em.persist(category);
    }
}
