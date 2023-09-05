import com.flightbooking.display.Display;
import com.flightbooking.model.plane.Flight;
import com.flightbooking.model.plane.PassengerClass;
import com.flightbooking.model.plane.Seat;
import com.flightbooking.passenger.BookingManager;
import com.flightbooking.passenger.Passenger;

public class FlightBookingApplication {
    public static void main(String[] args) {

        Flight flight = new Flight();
        flight.initialize();
        //flight.printSeatingMap();

        //

        Display display = new Display(flight);

        PassengerClass passengerClass = display.promptUserToSeletCabin();

  //this value is coming back null and needs to be fixed in the code
        //
        Seat userSeatSelection =    flight.chooseSeatByTypeGivenClass(passengerClass);

        System.out.println("userSeatSelection: "+userSeatSelection);

        Passenger passenger = new Passenger();

        passenger.createPassenger();
        System.out.println("passenger: "+passenger);

 //= flight.fetchSeat(passengerClass,)
        System.out.println("reserving seat with the passenger info +seat");
    //    System.out.println("seat: +seat");

        System.out.println("seat before register: " + userSeatSelection);

        userSeatSelection.setPassenger(passenger);
        userSeatSelection.setBooked(true);

        System.out.println("seat after register  " + userSeatSelection);

        System.out.println("seat map");
flight.displayClassSeats();

        //first
        // System.out.println("seat: +seat");
        if (true) System.exit(0);   //


        //   --      display.printSeatMap(PassengerClass.FIRST);

        BookingManager bookingManager = new BookingManager(flight);


    }

}
