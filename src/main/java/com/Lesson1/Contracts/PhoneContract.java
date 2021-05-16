package com.Lesson1.Contracts;

import com.Lesson1.Clients.Client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneContract extends BasicContract {

    private int smsCount;
    private int minuteCount;
    private int internetCount;


    public PhoneContract(int smsCount, int minuteCount, int internetCount, Client client, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(client, startDateTime, endDateTime);
        this.internetCount = internetCount;
        this.minuteCount = minuteCount;
        this.smsCount = smsCount;
    }

    PhoneContract(){}
    public int getInternetCount() {
        return internetCount;
    }

    public int getMinuteCount() {
        return minuteCount;
    }

    public int getSmsCount() {
        return smsCount;
    }
}
