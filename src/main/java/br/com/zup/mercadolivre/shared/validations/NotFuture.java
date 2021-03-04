package br.com.zup.mercadolivre.shared.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NotFutureValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotFuture {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "{custom.notfuture.message}";
    String fieldName();
    Class<?> domainClass();
}
