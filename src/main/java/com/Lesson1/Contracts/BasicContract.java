package com.Lesson1.Contracts;

import com.Lesson1.Clients.Client;
import com.Lesson1.JAXB.LocalDateParser;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlSeeAlso(value = {DigitalTVContract.class, PhoneContract.class, WiredInternetContract.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class BasicContract implements Comparable<BasicContract> {

    @XmlJavaTypeAdapter(value = LocalDateParser.class)
    @XmlElement(name = "startDateTime")
    private LocalDateTime startDateTime;

    @XmlJavaTypeAdapter(value = LocalDateParser.class)
    @XmlElement(name = "endDateTime")
    private LocalDateTime endDateTime;

    @XmlElement(name = "client")
    private Client client;

    @XmlElement(name = "contractNumber")
    private int contractNumber;

    BasicContract(){}

    protected BasicContract(Client client, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.client = client;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
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