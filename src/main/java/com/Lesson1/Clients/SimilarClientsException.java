package com.Lesson1.Clients;

/**
 * This exception throws, when program fpund similar clients
 */
public class SimilarClientsException extends Exception {
    private int passNumber;
    private int passSeries;

    public int getPassSeries() {
        return passSeries;
    }

    public int getPassNumber() {
        return passNumber;
    }

    public SimilarClientsException(String message) {
        super(message);
    }

    public SimilarClientsException(String message, int passNumber, int passSeries) {
        super(message);
        this.passNumber = passNumber;
        this.passSeries = passSeries;
    }

}