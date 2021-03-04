package br.com.zup.mercadolivre.shared.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class NotFutureValidator implements ConstraintValidator<NotFuture, Object> {
    private String fieldName;
    private Class<?> domainClass;

    @Override
    public void initialize(NotFuture constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
        domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        // Assert.state(LocalDateTime.parse(o.toString()).isBefore(LocalDateTime.now()), "This date can not be in the future");
        return LocalDateTime.parse(o.toString()).isBefore(LocalDateTime.now()) ;
    }
}
