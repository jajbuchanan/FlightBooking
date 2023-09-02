package Passenger;
import Display.*;
import Plane.*;
import Logic.*;

import java.util.*;

public class BookingManager {
    private Flight flight;
    private UserInput input = new UserInput(null);
    private List<Booking> bookings = new ArrayList<>();
    private InputLogic logic;

    public BookingManager(Flight flight) {
        this.flight = flight;
        this.input = new UserInput(this.flight);
        this.logic = new InputLogic(this.flight);
        this.bookings = new ArrayList<>();
    }
    public void newBooking() {
        // get new passenger information
        Passenger passenger = new Passenger();
        passenger = passenger.createPassenger();



        // get seat number
        String seatNumber = input.seatNumberInput();
        int seatRow = logic.returnSeatRow(seatNumber);
        PassengerClass cabinClass = logic.findSeatClass(seatNumber);
        char seatLetter = logic.returnSeatLetter(seatNumber);
        int seatLetterIndex = flight.classSeatLetterIndex(cabinClass, seatLetter);
        int relativeRow = logic.getRelativeSeatRow(cabinClass, seatRow);
        Seat[][] bookedCabin = flight.firstClassCabin.


        // input information into a new Booking
        Booking booking = new Booking(passenger, seat);
        bookings.add(booking);
        // output result

        System.out.println("Booking created as follows: ");
        System.out.println("Passenger first name: " + seat.getPassenger().getFirstName());
        System.out.println("Passenger last name: " + seat.getPassenger().getLastName());
        System.out.println("Passenger passport number: " + seat.getPassenger().getPassportNumber());
        System.out.println("Seat booked: " + seat.getSeatRow() + seat.getSeatLetter());
        System.out.println("Booking reference: " + booking.getBookingID());



       // String seatInput = input.seatNumberInput();
    }
}
