package com.Lesson1.Contracts;

import com.Lesson1.Clients.Client;

import java.time.LocalDateTime;

public abstract class BasicContract implements Comparable<BasicContract> {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final Client client;
    private int contractNumber;


    protected BasicContract( Client client, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.client = client;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public Client getClient() {
        return client;
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