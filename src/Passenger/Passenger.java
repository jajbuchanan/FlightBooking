package Passenger;
import plane.*;
import java.util.*;

public class Passenger {
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String seatNumber;

    Scanner keyboard = new Scanner(System.in);

    public Passenger() {
        this.firstName = "";
        this.lastName = "";
        this.passportNumber = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + passportNumber + ") " + seatNumber;
    }

    public void passengerFirstNameInput() {
        System.out.println("Enter passenger first name: ");
        setFirstName(keyboard.next());
    }

    public void passengerLastNameInput() {
        System.out.println("Enter passenger last name: ");
        setLastName(keyboard.next());
    }

    public void passengerPassportNumberInput() {
        String input;
        while (true) {
            System.out.println("Enter passport number: ");
            input = keyboard.nex();
            try {
                if (input.length() == 9) {
                    setPassportNumber(input);
                }
            } catck (IllegalArgumentException e) {
                System.out.println("Invalid input. Passport number must be 9 characters in length.");
            }
        }
    }

    public Passenger createPassenger() {
        Passenger passenger = new Passenger();
        createPassenger().passengerFirstNameInput();
        createPassenger().passengerLastNameInput();
        createPassenger().passengerPassportNumberInput();
        return passenger;
    }
}