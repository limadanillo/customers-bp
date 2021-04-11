package com.br.brasilprev.customers.core.constrain;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Constraint criada para ser possível habilitar validações customizadas
 *
 * @author Danillo Lima
 * @since 09/04/2021
 */
@Constraint(validatedBy = ZipCodeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR,
        ElementType.FIELD,
        ElementType.TYPE,
        ElementType.METHOD,
        ElementType.PARAMETER})
public @interface ZipCodeConstraint {
    String message() default "Cep inválido!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
