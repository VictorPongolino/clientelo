package br.com.alura.clientelo.modal.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(value = {ElementType.METHOD,ElementType.FIELD})
@Constraint(validatedBy = TelefoneValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Telefone {
    String message() default "Número de telefone incorreto";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
