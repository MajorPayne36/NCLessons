package com.Lesson1.Contracts;

import com.Lesson1.Clients.Client;

import java.time.LocalDateTime;

public abstract class BasicContract implements Comparable<BasicContract> {
    private final String contractType;
    private final Integer contractId;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final Client client;
    private int contractNumber;


    protected BasicContract(int contractId, Client client, String contractType) {
        this.contractType = contractType;
        this.client = client;
        this.contractId = contractId;
        this.startDateTime = LocalDateTime.now();
        this.endDateTime = this.startDateTime.plusYears(1);
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public String getContractType() {
        return contractType;
    }

    public Client getClient() {
        return client;
    }

    public Integer getContractId() {
        return contractId;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    @Override
    public int compareTo(BasicContract o) {
        return this.getContractNumber() - o.getContractNumber();
    }
}