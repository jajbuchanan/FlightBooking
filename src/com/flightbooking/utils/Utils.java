package com.flightbooking.utils;

public class Utils {
    public boolean charInArray(char[] array, char target) {
        for (char c : array) {
            if (c == target) {
                return true;
            }
        }
        return false;
    }

}