package com.Lesson1.Contracts;

import com.Lesson1.Clients.Client;

import java.time.LocalDateTime;


public class WiredInternetContract extends BasicContract {

    private final int maxSpeed;


    public WiredInternetContract(Client client, int maxSpeed, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(client, startDateTime, endDateTime);
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
