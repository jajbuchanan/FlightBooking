package com.flightbooking.passenger;

import com.flightbooking.display.UserInput;
import com.flightbooking.logic.InputLogic;
import com.flightbooking.model.plane.Flight;
import com.flightbooking.model.plane.PassengerClass;
import com.flightbooking.model.plane.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingManager {
    private Flight flight;
    private UserInput input = new UserInput(null);
    private List<Booking> bookings = new ArrayList<>();
    private InputLogic logic;

    private Scanner keyboard = new Scanner(System.in);


    public BookingManager(Flight flight) {
        this.flight = flight;
        this.input = new UserInput(this.flight);
        this.logic = new InputLogic(this.flight);
        this.bookings = new ArrayList<>();
    }

    public void newBooking() {
        while (true) {
            // get new passenger information - checked and works
            Passenger passengerInput = new Passenger();
            passengerInput.createPassenger();
            passengerInput.getPassengerDetails();

            // get seat number
            String seatNumber = input.seatNumberInput();
            // System.out.println("Seat number entered: " + String.valueOf(seatNumber));

            int seatRow = logic.returnSeatRow(seatNumber);
            //System.out.println("Seat row: " + String.valueOf(seatRow));
            PassengerClass passengerClass = logic.lookupSeatClassBySpecificSeat(seatNumber);
            //System.out.println("com.flightbooking.Passenger class: " + String.valueOf(passengerClass));
            char seatLetter = logic.returnSeatLetter(seatNumber);
            //System.out.println("Seat letter: " + String.valueOf(seatLetter));
            int seatLetterIndex = logic.returnSeatLetterRelativeIndex(passengerClass, seatLetter);
            //System.out.println("Seat letter index from " + String.valueOf(passengerClass) + ": " +String.valueOf(seatLetterIndex));
            int relativeRow = logic.getRelativeSeatRow(passengerClass, seatRow);
            //System.out.println("Relative row in " + String.valueOf(passengerClass) + ": " + String.valueOf(relativeRow));

            flight.bookSeat(passengerClass, relativeRow, seatLetterIndex, passengerInput);

            Seat bookedSeat = flight.getClassCabin(passengerClass).seats[relativeRow][seatLetterIndex];
            bookedSeat.seatInfo();


            // input information into a new Booking
            Booking booking = new Booking(passengerInput, bookedSeat);
            bookings.add(booking);
            // output result

            System.out.println("Booking created as follows: ");
            System.out.println("com.flightbooking.Passenger first name: " + bookedSeat.getPassenger().getFirstName());
            System.out.println("com.flightbooking.Passenger last name: " + bookedSeat.getPassenger().getLastName());
            System.out.println("com.flightbooking.Passenger passport number: " + bookedSeat.getPassenger().getPassportNumber());
            System.out.println("Seat booked: " + bookedSeat.getSeatRow() + bookedSeat.getSeatLetter());
            System.out.println("Booking reference: " + booking.getBookingID());


            System.out.println("Would you like to book another seat? (y/n)");
            char userChoice = keyboard.next().charAt(0);
            if (userChoice == 'n') {
                break;
            }
        }


        // String seatInput = input.seatNumberInput();
    }
}
