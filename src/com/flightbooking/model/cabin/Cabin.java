package com.flightbooking.model.cabin;

import com.flightbooking.model.plane.Seat;
import com.flightbooking.model.plane.SeatType;

public abstract class Cabin {
    public Seat[][] seats;

    public abstract int getNumberOfRows();

    public abstract void printSeatingChart();

    public abstract char[] getSeatLetters();

    public abstract void printSeatsBySeatType(SeatType seatType);

    public abstract void initializeSeats();
    public int deriveSeatNumberForSeatLater(char seatLetter) {
        for (int i = 0; i < this.getSeatLetters().length; i++) {
            if (this.getSeatLetters()[i] == seatLetter) {
                return i;
            }
        }
        return -1; // return -1 if the seat letter is not found
    }
    public int logicalRowNumberOffset;

    public int getLogicalRowNumberOffset() {
        return logicalRowNumberOffset;
    }

    public void setLogicalRowNumberOffset(int logicalRowNumberOffset) {
        this.logicalRowNumberOffset = logicalRowNumberOffset;
    }
}
