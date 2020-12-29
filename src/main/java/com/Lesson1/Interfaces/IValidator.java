package com.Lesson1.Interfaces;

import com.Lesson1.Contracts.BasicContract;
import com.Lesson1.validators.ValidatorResult;

public interface IValidator {
    public ValidatorResult validate (BasicContract contract);
}
