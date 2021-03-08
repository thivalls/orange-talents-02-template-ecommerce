package br.com.zup.mercadolivre.product;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class CheckFeaturesEqualsValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProductRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return ;
        }

        ProductRequest request = (ProductRequest) target;
        Set<String> equalsNames = request.searchEqualsFeatures();
        if(!equalsNames.isEmpty()) {
            errors.rejectValue("features", null, "This or these feature(s) is/are equals: " + equalsNames);
        }
    }
}
