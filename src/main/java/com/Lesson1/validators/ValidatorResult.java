package com.Lesson1.validators;

import com.Lesson1.Contracts.BasicContract;

import java.lang.reflect.Field;

public class ValidatorResult {
    private ValidationStatus status;
    private String message;
    private BasicContract contract;


    ValidatorResult(BasicContract contract, ValidationStatus status, String message) {
        this.message = message;
        this.status = status;
        this.contract = contract;
    }

    public String getMessage() {
        return message;
    }

    public ValidationStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String clientName = contract.getClient().getFirstName() + " " + contract.getClient().getSecondName();
        sb.append(clientName).append(": ").append(contract.getClass().getSimpleName());
        sb.append(" || message: ").append(message);
        return sb.toString();
    }
}
