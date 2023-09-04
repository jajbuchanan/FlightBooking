import com.flightbooking.display.Display;
import com.flightbooking.display.UserInput;
import com.flightbooking.model.plane.Flight;
import com.flightbooking.model.plane.PassengerClass;
import com.flightbooking.passenger.BookingManager;
import com.flightbooking.passenger.Passenger;

public class FlightBookingApplication {
    public static void main(String[] args) {

        Flight flight = new Flight();
        flight.initialize();
        //flight.printSeatingMap();

        //

        Display display = new Display(flight);

        PassengerClass passengerClass = display.displayClassSeats();

        display.chooseSeatByTypeGivenClass(passengerClass);
        // bookingManager.newBooking();

        if (true) System.exit(0);

        // pass the flight instance to the UserInterface method
        UserInput input = new UserInput(flight);
//

        Passenger passenger = new Passenger();

        passenger.createPassenger();

        //


        //   --      display.printSeatMap(PassengerClass.FIRST);

        BookingManager bookingManager = new BookingManager(flight);


    }

}
