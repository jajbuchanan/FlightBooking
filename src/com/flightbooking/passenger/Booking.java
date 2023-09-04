package com.flightbooking.passenger;

import com.flightbooking.model.plane.Seat;

public class Booking {
    // details booking information

    private static int bookingCounter = 44523; // for unique booking ID
    private final int bookingID;

    // details passenger information
    private Passenger passenger;

    // details seat information
    private int passengerRelativeRow;
    private String seatNumber;
    private Seat seat; // can obtain seatRow and seatLetter through Seat


    public Booking(Passenger passenger, Seat seat) {
        this.passenger = passenger;
        this.seat = seat;
        this.seat.bookSeat(passenger);
        this.bookingID = bookingCounter++;
    }

    public static int getBookingCounter() {
        return bookingCounter;
    }

    public static void setBookingCounter(int bookingCounter) {
        Booking.bookingCounter = bookingCounter;
    }

    public String bookingRef() {
        return String.valueOf("BR" + bookingID);
    }

    public Passenger getPassenger() {
        return this.passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getPassengerRelativeRow() {
        return passengerRelativeRow;
    }

    public void setPassengerRelativeRow(int passengerRelativeRow) {
        this.passengerRelativeRow = passengerRelativeRow;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }


}
