package com.flightbooking.model.cabin;

import com.flightbooking.model.plane.PassengerClass;
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

            System.out.print((rowNumber+this.logicalRowNumberOffset) + "\t");

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

    @Override
    public void initializeSeats() {

        this.seats = new Seat[this.getNumberOfRows()][this.getSeatLetters().length];

        // set initial values for initialised seats

        for (int cabinRowNumber = 0; cabinRowNumber < this.getNumberOfRows(); cabinRowNumber++) {

            for (int cabinRowSeatNumber = 0; cabinRowSeatNumber < this.getSeatLetters().length; cabinRowSeatNumber++) {

                char cabinRowSeatLetter = this.getSeatLetters()[cabinRowSeatNumber];

                Seat newSeat = new Seat();

                newSeat.setSeatRow(cabinRowNumber);
                newSeat.setSeatLetter(cabinRowSeatLetter);
                newSeat.setSeatNumber(cabinRowNumber+this.logicalRowNumberOffset, cabinRowSeatLetter);
                newSeat.setPassengerClass(PassengerClass.BUSINESS);

                if (cabinRowSeatLetter == 'A' || cabinRowSeatLetter == 'H') {
                    newSeat.setType(SeatType.WINDOW);
                   } else if (cabinRowSeatLetter == 'C' || cabinRowSeatLetter == 'F') {
                    newSeat.setType(SeatType.MIDDLE);
            } else {
                    newSeat.setType(SeatType.AISLE);
                }

                seats[cabinRowNumber][cabinRowSeatNumber] = newSeat;
            }
        }
    }

}
