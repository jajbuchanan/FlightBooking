package Display;
import Plane.*;
import java.util.*;
public class Display {
    private Flight flight;
    private Scanner keyboard;

    public Display(Flight flight) {
        this.flight = flight;
        this.keyboard = new Scanner(System.in);
    }

    public void displayClassSeats() {
        System.out.println("Enter the cabin class (First, Business, Traveller): ");
        String classChoice = keyboard.next().toUpperCase();

        try {
            PassengerClass chosenClass = PassengerClass.valueOf(classChoice);
            Seat[][] seats = flight.displayClassSeats(PassengerClass.valueOf(classChoice));
            int startRow = flight.getClassCabin(chosenClass).getStartRow();
            printSeatMap(chosenClass, seats, startRow);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter a valid class.");
        }
    }

    public void printSeatMap(PassengerClass pClass, Seat[][] seats, int startRow) {
        switch (pClass) {
            case FIRST -> System.out.println("\tA\tD\tE\tH");
            case BUSINESS -> System.out.println("\tA\tC\tD\tE\tF\tH");
            case TRAVELLER -> System.out.println("\tA\tB\tC\tD\tE\tF\tG\tH");
            default -> throw new IllegalArgumentException("Invalid class type: " + pClass);
        }

        for (int i = 0; i < seats.length; i++) {
            System.out.println(startRow + i + "\t");
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].isBooked()) {
                    System.out.println("  X\t");
                } else {
                    System.out.println(seats[i][j].getType() + "\t");
                }
            }
            System.out.println();
        }
    }
}
