package com.Lesson1.Clients;

import java.time.LocalDateTime;

public class Client {

    private int id;
    private final String firstName;
    private final String secondName;
    private final LocalDateTime birthday;
    private final int passSeries;
    private final int passNumber;
    private final String gender;

    public Client(int id,
                  String firstName,
                  String secondName,
                  LocalDateTime birthday,
                  int passSeries,
                  int passNumber,
                  String gender) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.passSeries = passSeries;
        this.passNumber = passNumber;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public int getPassNumber() {
        return passNumber;
    }

    public int getPassSeries() {
        return passSeries;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientAge() {
        return LocalDateTime.now().getYear() - this.birthday.getYear();
    }
}