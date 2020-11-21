package com.Lesson1.Contracts;

import com.Lesson1.Clients.Client;

public class PhoneContract extends BasicContract {

    private final int smsCount;
    private final int minuteCount;
    private final int internetCount;


    public PhoneContract(int smsCount, int minuteCount, int internetCount, Client client) {
        super(client);
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
