package edu.hanover.schedulevisualizer.core.entity;

import java.util.ArrayList;
import java.util.List;

public enum Weekday {
    Monday("M"),
    Tuesday("T"),
    Wednesday("W"),
    Thursday("R"),
    Friday("F");

    private final String abbr;

    Weekday(final String abbr) {
        this.abbr = abbr;
    }

    public static List<Weekday> MWF() {
        return List.of(Monday, Wednesday, Friday);
    }

    public static List<Weekday> MW() {
        return List.of(Monday, Wednesday);
    }

    public static List<Weekday> TR() {
        return List.of(Tuesday, Thursday);
    }

    public static List<Weekday> fromLetters(String s) {
        String[] weekdays = s.split("");
        List<Weekday> weekdayList = new ArrayList<>();
        for (String wkd : weekdays) {
            switch (wkd) {
                case "M" -> weekdayList.add(Monday);
                case "T" -> weekdayList.add(Tuesday);
                case "W" -> weekdayList.add(Wednesday);
                case "R" -> weekdayList.add(Thursday);
                case "F" -> weekdayList.add(Friday);
            }
        }
        return weekdayList;
    }

    public String toShortString() {
        return this.abbr;
    }

    public boolean isTR() {
        return this == Tuesday || this == Thursday;
    }
}
