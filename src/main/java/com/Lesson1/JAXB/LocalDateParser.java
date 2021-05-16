package com.Lesson1.JAXB;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateParser extends XmlAdapter<String, LocalDateTime> {

    /**
     * Unmarshalling date from string
     *
     * @param date object to marshal
     * @return unmarshalled date
     */
    public LocalDateTime unmarshal(String date) {
        return LocalDateTime.parse(date);
    }

    /**
     * Marshalling date to String
     *
     * @param date date to marshal
     * @return LocalDate as String
     */
    public String marshal(LocalDateTime date) {
        return date.toString();
    }
}
