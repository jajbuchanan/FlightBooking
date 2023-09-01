package Plane;

public class BusinessClassCabin extends Cabin {
    public BusinessClassCabin(int startRow) {
        super(startRow);
        numRows = 15;
        seatLetters = new char[] {'A', 'C', 'D', 'E', 'F', 'H'};
    }

}
