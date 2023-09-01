import Plane.*;
import Display.*;

public class Main {
    public static void main(String[] args) {

        Flight flight = new Flight();
        flight.initialize();

        // pass the flight instance to the UserInterface method
        UserInput input = new UserInput(flight);
        Display display = new Display(flight);

        flight.printAllCabins();

        input.userSeatBooking();

        flight.printAllCabins();

        //  	display.displayClassSeats();



    }
}