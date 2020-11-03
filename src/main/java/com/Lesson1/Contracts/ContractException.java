package com.Lesson1.Contracts;

public class ContractException extends Exception {

    public ContractException (){
        super ("This contract didn't found");
    }
    public ContractException(String message) {
        super(message);
    }
}