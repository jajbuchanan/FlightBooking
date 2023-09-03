import Passenger.BookingManager;
import Passenger.Passenger;
import Plane.*;
import Display.*;

public class Main {
    public static void main(String[] args) {

        Flight flight = new Flight();
        flight.initialize();

        // pass the flight instance to the UserInterface method
        UserInput input = new UserInput(flight);
        Display display = new Display(flight);
        BookingManager bookingManager = new BookingManager(flight);

        Passenger newPassenger = new Passenger();
        newPassenger.createPassenger();
        newPassenger.getPassengerDetails();

        // bookingManager.newBooking();

    }
}