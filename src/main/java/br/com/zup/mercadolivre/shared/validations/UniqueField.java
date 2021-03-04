package br.com.zup.mercadolivre.shared.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueFieldValidator.class)
public @interface UniqueField {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "{custom.unique.field.validation}";
    String fieldName();
    Class<?> domainClass();
 }
