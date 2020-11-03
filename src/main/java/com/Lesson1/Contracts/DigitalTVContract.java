package com.Lesson1.Contracts;


import com.Lesson1.Clients.Client;

public class DigitalTVContract extends BasicContract {

    private TVPackages tvPackage;



    public DigitalTVContract(Client client, TVPackages tvPackage, int contractId) {
        super(contractId, client, "DigitalTVContract");
        this.tvPackage = tvPackage;

    }

    public TVPackages getTvPackage() {
        return tvPackage;
    }
}