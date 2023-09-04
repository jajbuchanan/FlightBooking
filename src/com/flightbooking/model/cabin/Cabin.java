package Plane;

public abstract class Cabin {
    protected int startRow;
    protected int numRows = 10; // Default value
    public char[] seatLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    public Seat[][] seats;

    public Cabin(int startRow) {
        this.startRow = startRow;
    }

    public abstract   void  printSeatingChgart();
    // declare method to initialise seats

    public void initializeSeats(String cabinClass) {

        seats = new Seat[numRows][seatLetters.length];

        // set initial values for initialised seats
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < seatLetters.length; j++) {

                seats[i][j] = new Seat();
                seats[i][j].setSeatRow(startRow + i); // because seat rows start from 1
                seats[i][j].setSeatLetter((seatLetters[j]));

                // assign seat types based on seat letters

                if (seatLetters[j] == 'A' || seatLetters[j] == 'H') {
                    seats[i][j].setType((SeatType.WINDOW) );
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

    // iterate through all of the seat letters in the seatLetters array to identify the index in the line
    public int getSeatLetterIndex(char letter) {
        for (int i = 0; i < seatLetters.length; i++) {
            if (seatLetters[i] == letter) {
                return i;
            }
        }
        return -1; // return -1 if the seat letter is not found
    }

    public int getStartRow() {
        return startRow;
    }

    // define method to print info for all seats in class

    public void cabinSeatInfo() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < seatLetters.length; j++) {
                seats[i][j].seatInfo();
            }
        }
    }
}
