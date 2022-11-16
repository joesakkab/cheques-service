package com.progressoft.validations;


import com.progressoft.repositories.ChequeRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class ChequeNumberValidator implements
        ConstraintValidator<UniqueChequeNumber, String> {
    private ChequeRepository chequeRepo;

    @Override
    public void initialize(UniqueChequeNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String chequeNumber, ConstraintValidatorContext cxt) {
        return !chequeRepo.existsChequeByNumber(chequeNumber);
    }
}
