package com.Lesson1.validators;

import com.Lesson1.ArayUtil;
import com.Lesson1.Contracts.BasicContract;
import com.Lesson1.Contracts.Contracts;

import java.util.ArrayList;

public class Validate {
    Contracts contracts;


    public Validate(Contracts contracts) {
        this.contracts = contracts;
    }

    public ArrayList<ValidatorResult> validateAllContracts() {
        ArrayList<ValidatorResult> results = new ArrayList<>();

        ValidateClientAge clientAge = new ValidateClientAge();
        ValidatePassNumber passNumber = new ValidatePassNumber();
        ValidatePassSeries passSeries = new ValidatePassSeries();

        for (int i = 0; i < ArayUtil.getArrayValuesCount(contracts.getContracts()); i++) {
            results.add(clientAge.validate(contracts.getContracts()[i]));
            results.add(passNumber.validate(contracts.getContracts()[i]));
            results.add(passSeries.validate(contracts.getContracts()[i]));
        }
        return results;
    }
}
