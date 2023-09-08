package com.flightbooking.trash.passenger;

import com.flightbooking.model.plane.PassengerClass;
import com.flightbooking.model.plane.SeatType;

import java.util.Scanner;

public class Passenger {
    Scanner keyboard = new Scanner(System.in);
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String seatNumber;
    private SeatType seatType;
    private PassengerClass passengerClass;

    public Passenger() {
        this.firstName = "";
        this.lastName = "";
        this.passportNumber = "";
    }

    public Passenger createPassenger() {
        Passenger passenger = new Passenger();
        this.passengerFirstNameInput();
        this.passengerLastNameInput();
        this.passengerPassportNumberInput();
        return passenger;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + passportNumber + ") " + seatNumber;
    }

    public void passengerFirstNameInput() {
        String input;
        while (true) {
            System.out.println("Enter passenger first name: ");
            input = keyboard.next();
            if (input.matches("^[a-zA-Z]+$")) {
                setFirstName(input);
                break;
            } else {
                System.out.println("Invalid input. First name should only contain letters from A-Z");
            }
        }
    }

    public void passengerLastNameInput() {
        String input;
        while (true) {
            System.out.println("Enter passenger last name: ");
            input = keyboard.next();
            if (input.matches("^[a-zA-Z]+$")) {
                setLastName(input);
                break;
            } else {
                System.out.println("Invalid input. First name should only contain letters from A-Z");
            }
        }
    }
    public void passengerPassportNumberInput() {
        String input;
        while (true) {
            System.out.println("Enter passport number: ");
            input = keyboard.next();
            try {
                if (input.length() == 9) {
                    setPassportNumber(input);
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Passport number must be 9 characters in length.");
            }
            System.out.println("Invalid passport number. Passport number must be 9 characters in length.");
        }
    }


    public void getPassengerDetails() {
        System.out.println("com.flightbooking.Passenger first name: " + getFirstName());
        System.out.println("com.flightbooking.Passenger last name: " + getLastName());
        System.out.println("com.flightbooking.Passenger passport number: " + getPassportNumber());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

}
