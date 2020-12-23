package com.Lesson1.Contracts;


import com.Lesson1.Clients.Client;

import java.time.LocalDateTime;

public class DigitalTVContract extends BasicContract {

    private String tvPackage;



    public DigitalTVContract(String tvPackage, Client client, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(client, startDateTime, endDateTime);
        this.tvPackage = tvPackage;

    }

    public String getTvPackage() {
        return tvPackage;
    }
}