package com.flightbooking.model.cabin;

import com.flightbooking.model.plane.Seat;
import com.flightbooking.model.plane.SeatType;

public abstract class Cabin {
    public Seat[][] seats;


    public abstract int getNumberOfRows();

    public abstract void printSeatingChart();

    public abstract char[] getSeatLetters();


    public abstract void printSeatsBySeatType(SeatType seatType);

    public void initializeSeats(String cabinClass) {

        seats = new Seat[this.getNumberOfRows()][this.getSeatLetters().length];

        // set initial values for initialised seats
        for (int i = 0; i < this.getNumberOfRows(); i++) {
            for (int j = 0; j < this.getSeatLetters().length; j++) {

                seats[i][j] = new Seat();
                //  seats[i][j].setSeatRow(startRow + i); // because seat rows start from 1
                seats[i][j].setSeatLetter((this.getSeatLetters()[j]));

                // assign seat types based on seat letters

                if (this.getSeatLetters()[j] == 'A' || this.getSeatLetters()[j] == 'H') {
                    seats[i][j].setType((SeatType.WINDOW));
                } else if (cabinClass.equals("business") && (this.getSeatLetters()[j] == 'C' || this.getSeatLetters()[j] == 'F')) {
                    seats[i][j].setType(SeatType.MIDDLE);
                } else if (this.getSeatLetters()[j] == 'B' || this.getSeatLetters()[j] == 'F') {
                    seats[i][j].setType(SeatType.STANDARD);
                } else if (cabinClass.equals("traveler") && (this.getSeatLetters()[j] == 'C' || this.getSeatLetters()[j] == 'F')) {
                    seats[i][j].setType(SeatType.STANDARD);
                } else {
                    seats[i][j].setType(SeatType.AISLE);
                }
            }
        }
    }


    // method to get the seat letters for each cabin

    // iterate through all of the seat letters in the seatLetters array to identify the index in the line
    public int getSeatLetterIndex(char letter) {
        for (int i = 0; i < this.getSeatLetters().length; i++) {
            if (this.getSeatLetters()[i] == letter) {
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

    private void cabinSeatInfo() {
        for (int i = 0; i < this.getNumberOfRows(); i++) {
            for (int j = 0; j < this.getSeatLetters().length; j++) {
                seats[i][j].seatInfo();
            }
        }
    }

}
