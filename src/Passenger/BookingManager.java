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
        Passenger passengerInput = passenger.createPassenger();
        System.out.println(passenger);



        // get seat number
        String seatNumber = input.seatNumberInput();
        int seatRow = logic.returnSeatRow(seatNumber);
        PassengerClass passengerClass = logic.findSeatClass(seatNumber);
        char seatLetter = logic.returnSeatLetter(seatNumber);
        int seatLetterIndex = flight.classSeatLetterIndex(passengerClass, seatLetter);
        int relativeRow = logic.getRelativeSeatRow(passengerClass, seatRow);
        flight.bookSeat(passengerClass, relativeRow, seatLetterIndex, passengerInput);

        Seat bookedSeat = flight.travellerClassCabin.seats[relativeRow][seatLetterIndex];
        bookedSeat.seatInfo();


        // input information into a new Booking
        Booking booking = new Booking(passenger, bookedSeat);
        bookings.add(booking);
        // output result

        System.out.println("Booking created as follows: ");
        System.out.println("Passenger first name: " + bookedSeat.getPassenger().getFirstName());
        System.out.println("Passenger last name: " + bookedSeat.getPassenger().getLastName());
        System.out.println("Passenger passport number: " + bookedSeat.getPassenger().getPassportNumber());
        System.out.println("Seat booked: " + bookedSeat.getSeatRow() + bookedSeat.getSeatLetter());
        System.out.println("Booking reference: " + booking.getBookingID());



       // String seatInput = input.seatNumberInput();
    }
}
