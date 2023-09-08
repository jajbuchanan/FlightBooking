package com.flightbooking.model.plane;

import com.flightbooking.model.passenger.Passenger;

public class Seat  implements Comparable<Seat>{
    private SeatType type;
    private boolean isBooked;
    private Passenger passenger;
    private int seatRow;
    private char seatLetter;
    private String seatNumber;


    // define a constructor for the class
    public Seat() {
        this.isBooked = false;
    }

    public void seatInfo() {
        System.out.println();
        System.out.println("Plane.Seat information for seat " + seatRow + seatLetter);
        System.out.println("Plane.Seat type: " + type);
        System.out.println("Plane.Seat is booked: " + isBooked);
        if (isBooked) {
            System.out.println("com.flightbooking.Passenger.com.flightbooking.Passenger first name: " + passenger.getFirstName());
            System.out.println("com.flightbooking.Passenger.com.flightbooking.Passenger last name: " + passenger.getLastName());
            System.out.println("Passport number: " + passenger.getPassportNumber());
        }
    }

    public void bookSeat(
            Passenger passengerInput) {
        passenger = passengerInput;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
        if (passenger != null) {
            this.isBooked = true;
        } else {
            this.isBooked = false;
        }
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public boolean isAvailable() {
        return !isBooked;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public char getSeatLetter() {
        return seatLetter;
    }

    public void setSeatLetter(char seatLetter) {
        this.seatLetter = seatLetter;
    }

    public String getSeatNumber() {
        return String.valueOf(seatRow + seatLetter);
    }

    public void setSeatNumber(int seatRow, char seatLetter) {
        this.seatNumber = String.valueOf(seatRow) + seatLetter;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "type=" + type +
                ", isBooked=" + isBooked +
                ", passenger=" + passenger +
                ", seatRow=" + seatRow +
                ", seatLetter=" + seatLetter +
                ", seatNumber='" + seatNumber + '\'' +
                '}';
    }
    private PassengerClass passengerClass;
    public void setPassengerClass(PassengerClass passengerClass) {this.passengerClass = passengerClass;
    }
    public PassengerClass getPassengerClass(){
        return this.passengerClass;
    }


    @Override
    public int compareTo(Seat other) {

        System.out.println("doing comparable things");
        if(this.passenger==null)return -1;
        if(other.passenger==null)return 1;
        System.out.println("not null");

        int val = this.passenger.getLastName().compareTo(other.passenger.getLastName());

        System.out.println("val: + "+val);
        return val;
    }
}
