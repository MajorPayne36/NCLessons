package com.Lesson1.Contracts;

import com.Lesson1.Clients.Client;


public class WiredInternetContract extends BasicContract {

    private final int maxSpeed;


    public WiredInternetContract(Client client, int maxSpeed) {
        super(client);
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
