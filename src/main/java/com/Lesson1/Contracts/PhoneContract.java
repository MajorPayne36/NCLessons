package com.Lesson1.Contracts;

import com.Lesson1.Clients.Client;

import java.time.LocalDateTime;

public class PhoneContract extends BasicContract {

    private final int smsCount;
    private final int minuteCount;
    private final int internetCount;


    public PhoneContract(int smsCount, int minuteCount, int internetCount, Client client, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(client, startDateTime, endDateTime);
        this.internetCount = internetCount;
        this.minuteCount = minuteCount;
        this.smsCount = smsCount;
    }

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
