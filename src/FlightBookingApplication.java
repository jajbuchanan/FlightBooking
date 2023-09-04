import Passenger.BookingManager;
import Passenger.Passenger;
import Plane.*;
import Display.*;

public class Main {
    public static void main(String[] args) {

        Flight flight = new Flight();
        flight.initialize();

        flight.printSeatingMap();

        if(true)System.exit(0);
        // pass the flight instance to the UserInterface method
        UserInput input = new UserInput(flight);
        Display display = new Display(flight);
        BookingManager bookingManager = new BookingManager(flight);

      //  display.printSeatMap(PassengerClass.FIRST);

        // bookingManager.newBooking();

    }

    public void printSeatingMap(){}
}
