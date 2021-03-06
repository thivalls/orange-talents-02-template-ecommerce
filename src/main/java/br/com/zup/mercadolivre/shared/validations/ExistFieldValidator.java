package br.com.zup.mercadolivre.shared.validations;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistFieldValidator implements ConstraintValidator<ExistField, Object> {
    private String fieldName;
    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = em.createQuery("select f from " + domainClass.getName() +  " f where " + fieldName + " = :fieldnamevalue");
        query.setParameter("fieldnamevalue", o);
        List resultList = query.getResultList();

        Assert.state(resultList.size() <= 1, "This " + domainClass.getName() + " entity is not valid");
        return !resultList.isEmpty();
    }

    @Override
    public void initialize(ExistField constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
        domainClass = constraintAnnotation.domainClass();
    }
}
