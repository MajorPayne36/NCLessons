package com.Lesson1.Clients;

import com.Lesson1.JAXB.LocalDateParser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class Client {

    private int id;
    private String firstName;
    private String secondName;

    @XmlJavaTypeAdapter(LocalDateParser.class)
    private LocalDateTime birthday;
    private int passSeries;
    private int passNumber;
    private String gender;

    public Client(
            String firstName,
            String secondName,
            LocalDateTime birthday,
            int passSeries,
            int passNumber,
            String gender) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.passSeries = passSeries;
        this.passNumber = passNumber;
        this.gender = gender;
    }

    Client(){}

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

    @Override
    public String toString() {
        return this.firstName + " " + this.secondName + " " + this.passSeries + " " + this.passNumber;
    }
}