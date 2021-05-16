package com.Lesson1.Contracts;

import com.Lesson1.Clients.Client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class WiredInternetContract extends BasicContract {

    private int maxSpeed;


    public WiredInternetContract(Client client, int maxSpeed, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(client, startDateTime, endDateTime);
        this.maxSpeed = maxSpeed;
    }

    WiredInternetContract(){}

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
