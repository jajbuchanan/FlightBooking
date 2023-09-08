/*
package com.flightbooking.trash;

import com.flightbooking.model.plane.Flight;

import com.flightbooking.model.plane.PassengerClass;
import com.flightbooking.model.plane.Seat;
import com.flightbooking.trash.passenger.Booking;
import com.flightbooking.model.passenger.Passenger;

import java.util.Scanner;

public class UserInput {
    Scanner keyboard = new Scanner(System.in);
    InputLogic logic;

    private Flight flight;

    // constructor

    public UserInput(Flight flight) {
        this.keyboard = new Scanner(System.in);
        this.flight = flight;
        this.logic = new InputLogic(flight); // initialize logic here after flight is set
    }

    public String seatNumberInput() {
        String input;
        while (true) {
            System.out.println("Enter the seat number (e.g. 7A): ");
            input = keyboard.next();
            try {
                if (logic.seatValidator(input)) {
                    return input;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Enter a valid seat.");
            }
            System.out.println("Enter a valid seat number.");
        }
    }

    // methods to get passenger input

    public String getPassengerClass() {
        String input;
        System.out.println("Enter the choice of class (First, Business, Traveller): ");
        input = keyboard.next().toUpperCase();

        try {
            PassengerClass passengerClass = PassengerClass.valueOf(input);
            return passengerClass.toString();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter a valid class.");
            throw new IllegalArgumentException("Invalid input for passenger class.");
        }
    }

    public char returnSeatLetter(String input) {
        return input.charAt(input.length() - 1);
    }

    public int returnSeatRow(String input) {
        String seatInput = input.toUpperCase();
        return Integer.parseInt(seatInput.substring(0, seatInput.length() - 1)); // -1 because array index starts at 0
    }

    public void userSeatBooking(Passenger passenger, Seat seat) {
        passenger = passenger.createPassenger();

        Booking booking = new Booking(passenger, seat);
    }


}
*/