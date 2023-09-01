package Passenger;
import Display.*;
import Plane.*;

import java.util.*;

public class BookingManager {
    private Flight flight;
    private UserInput input = new UserInput(flight);
    private List<Booking> bookings = new ArrayList<>();

    public void bookSeat(Passenger passenger, Seat seat) {
        passenger.passengerFirstNameInput();
        passenger.passengerLastNameInput();
        passenger.passengerPassportNumberInput();
        String seatInput = input.seatNumberInput();
        bookings.add(new Booking(passenger, seat));
    }
}
