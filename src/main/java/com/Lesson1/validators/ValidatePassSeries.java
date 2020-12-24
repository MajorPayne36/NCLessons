package com.Lesson1.validators;

import com.Lesson1.Contracts.BasicContract;

public class ValidatePassSeries implements IValidator{
    /**
     * Validate contract's client pass series
     * @param contract which need in validation
     * @return ValidatorResult
     */
    @Override
    public ValidatorResult validate(BasicContract contract) {
        int series = contract.getClient().getPassSeries();
        if (series>999 && series <= 9999)
            return new ValidatorResult(contract, ValidationStatus.OK, "");
        else
            return new ValidatorResult(contract, ValidationStatus.ERROR, "The client pass series not correct");
    }
}
