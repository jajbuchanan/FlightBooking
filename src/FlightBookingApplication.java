import com.flightbooking.display.Display;
import com.flightbooking.display.UserInput;
import com.flightbooking.model.plane.Flight;
import com.flightbooking.passenger.BookingManager;

public class FlightBookingApplication {
    public static void main(String[] args) {

        Flight flight = new Flight();
        flight.initialize();

        flight.printSeatingMap();

        if (true) System.exit(0);
        // pass the flight instance to the UserInterface method
        UserInput input = new UserInput(flight);
        Display display = new Display(flight);
        BookingManager bookingManager = new BookingManager(flight);

        //  display.printSeatMap(PassengerClass.FIRST);

        // bookingManager.newBooking();

    }

}
