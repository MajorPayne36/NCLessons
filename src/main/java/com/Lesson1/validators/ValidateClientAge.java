package com.Lesson1.validators;

import com.Lesson1.Contracts.BasicContract;

public class ValidateClientAge implements IValidator{
    /**
     * Validate contract's client pass series
     * @param contract which need in validation
     * @return ValidatorResult
     */
    @Override
    public ValidatorResult validate(BasicContract contract) {
        if (contract.getClient().getClientAge() >= 18)
            return new ValidatorResult(contract, ValidationStatus.OK, "");
        else
            return new ValidatorResult(contract, ValidationStatus.ERROR, "The client so little for creating contracts");
    }
}
