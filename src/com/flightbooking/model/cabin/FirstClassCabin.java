package com.flightbooking.model.cabin;

import com.flightbooking.model.plane.Seat;

public class FirstClassCabin extends Cabin {

    public FirstClassCabin(int startRow) {

        super(startRow);


        seatLetters = new char[]{'A', 'D', 'E', 'H'};

    }

    public int getNumberOfRows() {
        return 3;
    }

    @Override
    public void printSeatingChart() {

        System.out.println("\t   A\t  D\t\t  E\t\t   H");

        for (int rowNumber = 0; rowNumber < this.getNumberOfRows(); rowNumber++) {
            System.out.print(startRow + rowNumber + "\t");

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
}
