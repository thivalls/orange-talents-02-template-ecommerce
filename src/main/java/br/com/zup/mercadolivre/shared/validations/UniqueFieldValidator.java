package br.com.zup.mercadolivre.shared.validations;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {
    private String fieldName;
    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(UniqueField constraintAnnotationParameters) {
        fieldName = constraintAnnotationParameters.fieldName();
        domainClass = constraintAnnotationParameters.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = em.createQuery("SELECT f FROM " + domainClass.getName() + " f WHERE " + fieldName + " = :field");
        query.setParameter("field", o);
        List resultList = query.getResultList();

        Assert.isTrue(resultList.size() <= 1, "This " + domainClass.getName() + " has already been saved");

        return resultList.isEmpty();
    }


}
