package com.progressoft.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ChequeNumberValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueChequeNumber {
    String message() default "ChequeEntity number must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
