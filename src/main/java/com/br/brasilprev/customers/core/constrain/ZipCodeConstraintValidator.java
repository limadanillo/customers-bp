package com.br.brasilprev.customers.core.constrain;

import com.br.brasilprev.customers.utils.ZipoCodeUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipCodeConstraintValidator implements ConstraintValidator<ZipCodeConstraint, String> {
    @Override
    public void initialize(ZipCodeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isBlank()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Cep não pode ser vazio ou nulo").addConstraintViolation();
            return false;
        }

        if(!ZipoCodeUtils.valid(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Cep inválido").addConstraintViolation();
            return false;
        }
        return true;
    }
}
