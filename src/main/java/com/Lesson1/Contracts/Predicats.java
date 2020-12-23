package com.Lesson1.Contracts;

import java.time.LocalDateTime;
import java.util.function.Predicate;

public class Predicats {
    /**
     * Only Digital TV Contracts
     *
     * @return
     */
    public static Predicate<BasicContract> isDigitalTVContract() {
        return p -> p.getClass().getSimpleName().equals(ContractTypes.digitalTV);
    }

    /**
     * Only Phone Contracts
     *
     * @return
     */
    public static Predicate<BasicContract> isPhoneContract() {
        return p -> p.getClass().getSimpleName().equals(ContractTypes.phoneContract);
    }

    /**
     * Only Wired Internet Contracts
     *
     * @return
     */
    public static Predicate<BasicContract> isWiredInternetContract() {
        return p -> p.getClass().getSimpleName().equals(ContractTypes.wiredInternetContract);
    }

    /**
     * Only working Contracts
     *
     * @return
     */
    public static Predicate<BasicContract> isWorking() {
        return p -> LocalDateTime.now().isAfter(p.getStartDateTime()) && LocalDateTime.now().isBefore(p.getEndDateTime());
    }

    /**
     * Only ended Contracts
     *
     * @return
     */
    public static Predicate<BasicContract> isEnded() {
        return p -> LocalDateTime.now().isAfter(p.getEndDateTime());
    }

    /**
     * find Contracts which start date year like transferred year
     *
     * @param year
     * @return
     */
    public static Predicate<BasicContract> thisYear(int year) {
        return p -> p.getStartDateTime().getYear() == year;
    }

    /**
     * find Contracts which start date like transferred date
     *
     * @param dt
     * @return
     */
    public static Predicate<BasicContract> thisDate(LocalDateTime dt) {
        return p -> p.getStartDateTime().equals(dt);
    }
}
