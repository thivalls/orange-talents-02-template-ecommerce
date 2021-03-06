package br.com.zup.mercadolivre.shared.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ExistFieldValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistField {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "{custom.exist.field.validaor}";
    String fieldName();
    Class<?> domainClass();
}
