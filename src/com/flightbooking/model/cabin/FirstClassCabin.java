package Plane;

public class FirstClassCabin extends Cabin {

    public FirstClassCabin(int startRow) {

        super(startRow);

        numRows = 3;

        seatLetters = new char[] {'A', 'D', 'E', 'H'};

    }
}
