package com.flightbooking.model.cabin;

import com.flightbooking.model.plane.Seat;
import com.flightbooking.model.plane.SeatType;

public abstract class Cabin {
    public char[] seatLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    public Seat[][] seats;
    //
    // protected int numRows = 10; // Default value
    //protected int startRow;

    /*public Cabin(int startRow) {
        this.startRow = startRow;
    }*/
    public abstract int getNumberOfRows();

    public abstract void printSeatingChart();
    // declare method to initialise seats

    public  abstract void  printSeatsBySeatType(SeatType seatType);

        public void initializeSeats(String cabinClass) {

        seats = new Seat[this.getNumberOfRows()][seatLetters.length];

        // set initial values for initialised seats
        for (int i = 0; i < this.getNumberOfRows(); i++) {
            for (int j = 0; j < seatLetters.length; j++) {

                seats[i][j] = new Seat();
              //  seats[i][j].setSeatRow(startRow + i); // because seat rows start from 1
                seats[i][j].setSeatLetter((seatLetters[j]));

                // assign seat types based on seat letters

                if (seatLetters[j] == 'A' || seatLetters[j] == 'H') {
                    seats[i][j].setType((SeatType.WINDOW));
                } else if (cabinClass.equals("business") && (seatLetters[j] == 'C' || seatLetters[j] == 'F')) {
                    seats[i][j].setType(SeatType.MIDDLE);
                } else if (seatLetters[j] == 'B' || seatLetters[j] == 'F') {
                    seats[i][j].setType(SeatType.STANDARD);
                } else if (cabinClass.equals("traveler") && (seatLetters[j] == 'C' || seatLetters[j] == 'F')) {
                    seats[i][j].setType(SeatType.STANDARD);
                } else {
                    seats[i][j].setType(SeatType.AISLE);
                }
            }
        }
    }


    // method to get the seat letters for each cabin

    public char[] getSeatLetters() {
        return seatLetters;
    }

    public void setSeatLetters(char[] seatLetters) {
        this.seatLetters = seatLetters;
    }

    // iterate through all of the seat letters in the seatLetters array to identify the index in the line
    public int getSeatLetterIndex(char letter) {
        for (int i = 0; i < seatLetters.length; i++) {
            if (seatLetters[i] == letter) {
                return i;
            }
        }
        return -1; // return -1 if the seat letter is not found
    }

    // define method to print info for all seats in class
/*
    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }*/

    public void cabinSeatInfo() {
        for (int i = 0; i < this.getNumberOfRows(); i++) {
            for (int j = 0; j < seatLetters.length; j++) {
                seats[i][j].seatInfo();
            }
        }
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

}
