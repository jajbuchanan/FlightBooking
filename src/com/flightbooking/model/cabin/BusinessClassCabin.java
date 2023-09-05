package com.flightbooking.model.cabin;

import com.flightbooking.model.plane.Seat;
import com.flightbooking.model.plane.SeatType;

public class BusinessClassCabin extends Cabin {



    @Override
    public int getNumberOfRows() {
        return 15;
    }

    @Override
    public void printSeatingChart() {

        System.out.println("\t   A\t   C\t  D\t\t  E\t\t   F\t   H");

        for (int rowNumber = 0; rowNumber < this.getNumberOfRows(); rowNumber++) {
            System.out.print(rowNumber + "\t");

            for (Seat seat : this.seats[rowNumber]) {
                if (seat.isBooked()) {
                    System.out.print("  X\t");
                } else {
                    System.out.print(seat.getType() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }

    }

    @Override
    public char[] getSeatLetters() {
        char[] seatLetters = new char[]{'A', 'C', 'D', 'E', 'F', 'H'};return seatLetters;
    }

    @Override
    public void printSeatsBySeatType(SeatType seatType) {

    }

}
