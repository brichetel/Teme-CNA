package service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ZodiacList {
    private String zodiac;
    private int monthStart;
    private int dayStart;
    private int monthEnd;
    private int dayEnd;

    ZodiacList(){}

    public ZodiacList(String zodiac, int monthStart, int dayStart, int monthEnd, int dayEnd) {
        this.zodiac = zodiac;
        this.monthStart = monthStart;
        this.dayStart = dayStart;
        this.monthEnd = monthEnd;
        this.dayEnd = dayEnd;
    }

    public String getZodiac() {
        return zodiac;
    }

    public int getMonthStart() {
        return monthStart;
    }

    public int getDayStart() {
        return dayStart;
    }

    public int getMonthEnd() {
        return monthEnd;
    }

    public int getDayEnd() {
        return dayEnd;
    }
}
