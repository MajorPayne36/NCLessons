package com.Lesson1.validators;

import com.Lesson1.Contracts.BasicContract;
import com.Lesson1.Interfaces.IValidator;

public class ValidatePassNumber implements IValidator {

    /**
     * Validate contract's client pass number
     * @param contract which need in validation
     * @return ValidatorResult
     */
    @Override
    public ValidatorResult validate(BasicContract contract) {
        int number = contract.getClient().getPassNumber();
        if (number > 99999 && number <= 999999)
            return new ValidatorResult(contract, ValidationStatus.OK, "");
        else
            return new ValidatorResult(contract, ValidationStatus.ERROR, "The client pass number not correct");
    }
}
