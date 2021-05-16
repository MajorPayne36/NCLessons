package com.Lesson1.Contracts;

import com.Lesson1.Clients.Client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class DigitalTVContract extends BasicContract {
    @XmlElement(name = "tvPackage")
    private String tvPackage;


    public DigitalTVContract(String tvPackage, Client client, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(client, startDateTime, endDateTime);
        this.tvPackage = tvPackage;

    }

    DigitalTVContract(){}

    public void setTvPackage(String tvPackage) {
        this.tvPackage = tvPackage;
    }

    public String getTvPackage() {
        return tvPackage;
    }
}