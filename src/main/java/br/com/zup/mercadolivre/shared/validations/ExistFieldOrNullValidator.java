package br.com.zup.mercadolivre.shared.validations;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistFieldOrNullValidator implements ConstraintValidator<ExistFieldOrNull, Object> {
    private String fieldName;
    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(ExistFieldOrNull constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
        domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null) return true;
        Query query = em.createQuery("SELECT 1 from " + domainClass.getName() + " WHERE " + fieldName + " = :value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();

        Assert.state(list.size() <= 1, "Entidade " + domainClass.getName() + " não existe");
        return !list.isEmpty();
    }
}
