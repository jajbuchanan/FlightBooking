package com.flightbooking.logic;

import com.flightbooking.model.plane.Flight;
import com.flightbooking.model.plane.PassengerClass;
import com.flightbooking.utils.Utils;

import java.util.Scanner;

public class InputLogic {
    Scanner keyboard = new Scanner(System.in);
    Utils utils = new Utils();
    private Flight flight;

    public InputLogic(Flight flight) {
        this.flight = flight;
    }

    public char returnSeatLetter(String input) {
        return input.charAt(input.length() - 1);
    }

    public int returnSeatRow(String input) {
        return Integer.parseInt(input.substring(0, input.length() - 1));
    }

    public int getRelativeSeatRow(
            PassengerClass pClass,
            int rowNum) {
        return switch (pClass) {
            case FIRST -> rowNum - 1;
            case BUSINESS -> rowNum - flight.getClassRows(PassengerClass.FIRST)
                    - 1;
            case TRAVELLER -> rowNum - (flight.getClassRows(PassengerClass.FIRST)
                    + flight.getClassRows(PassengerClass.BUSINESS))
                    - 1;
            default -> throw new IllegalArgumentException("Invalid class type: " + pClass);
        };
    }

    // Method to ask user for a seat number - returns the class the seat is in:
    public PassengerClass returnClass() {
        String input;
        System.out.println("Enter a seat number: (e.g. 15B) ");
        input = keyboard.next();
        return findSeatClass(input);
    }

    // Method to determine cabin class from user input
    public PassengerClass findSeatClass(String seatInput) {
        int rowNum = Integer.parseInt(seatInput.substring(
                0, seatInput.length() - 1));
        if (rowNum <= flight.getClassRows(PassengerClass.FIRST)) {
            return PassengerClass.FIRST;
        } else if (rowNum <= flight.getClassRows(
                PassengerClass.FIRST)
                + flight.getClassRows(PassengerClass.BUSINESS)) {
            return PassengerClass.BUSINESS;
        } else if (rowNum <= flight.getClassRows(
                PassengerClass.FIRST)
                + flight.getClassRows(PassengerClass.BUSINESS)
                + flight.getClassRows(PassengerClass.TRAVELLER)) {
            return PassengerClass.TRAVELLER;
        } else {
            // Throw new error or handle accordingly
            throw new IllegalArgumentException("Invalid seat input: " + seatInput);
        }
    }

    public int findClassRow(
            PassengerClass passengerClass,
            String seatInput) {
        int classRow;
        int rawRowNum = Integer.parseInt(seatInput.substring(
                0, seatInput.length() - 1));
        return switch (passengerClass) {
            case FIRST -> {
                classRow = rawRowNum;
                yield classRow;
            }
            case BUSINESS -> {
                classRow =
                        rawRowNum
                                - flight.getClassRows(PassengerClass.FIRST);
                yield classRow;
            }
            case TRAVELLER -> {
                classRow =
                        rawRowNum
                                - flight.getClassRows(PassengerClass.FIRST)
                                - flight.getClassRows(PassengerClass.BUSINESS);
                yield classRow;
            }
        };
    }

    public int returnSeatLetterRelativeIndex(
            PassengerClass passengerClass,
            char seatLetter) {
        return switch (passengerClass) {
            case FIRST -> flight.getClassCabin(PassengerClass.FIRST).getSeatLetterIndex(seatLetter);
            case BUSINESS -> flight.getClassCabin(PassengerClass.BUSINESS).getSeatLetterIndex(seatLetter);
            case TRAVELLER -> flight.getClassCabin(PassengerClass.TRAVELLER).getSeatLetterIndex(seatLetter);
        };
    }

    public boolean seatValidator(String seatNum) {
        int num = returnSeatRow(seatNum);
        char letter = returnSeatLetter(seatNum);
        PassengerClass seatClass = findSeatClass(seatNum);
        int numRows;
        char[] rowLetters;
        switch (seatClass) {
            case FIRST -> {
                numRows = flight.getClassRows(PassengerClass.FIRST);
                rowLetters = flight.classSeatLetters(PassengerClass.FIRST);
                return num <= numRows && utils.charInArray(rowLetters, letter);
            }
            case BUSINESS -> {
                numRows = flight.getClassRows(PassengerClass.FIRST)
                        + flight.getClassRows(PassengerClass.BUSINESS);
                rowLetters = flight.classSeatLetters(PassengerClass.BUSINESS);
                return num <= numRows && utils.charInArray(rowLetters, letter);
            }
            case TRAVELLER -> {
                numRows = flight.getClassRows(PassengerClass.FIRST)
                        + flight.getClassRows(PassengerClass.BUSINESS)
                        + flight.getClassRows(PassengerClass.TRAVELLER);
                rowLetters = flight.classSeatLetters(PassengerClass.TRAVELLER);
                return num <= numRows && utils.charInArray(rowLetters, letter);
            }
            default -> {
                return false;
            }
        }
    }

}
