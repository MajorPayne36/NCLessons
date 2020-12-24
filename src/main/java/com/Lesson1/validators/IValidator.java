package com.Lesson1.validators;

import com.Lesson1.Contracts.BasicContract;

public interface IValidator {
    public ValidatorResult validate (BasicContract contract);
}
