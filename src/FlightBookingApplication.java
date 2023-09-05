import com.flightbooking.model.flight.Flight;
import com.flightbooking.model.plane.PassengerClass;
import com.flightbooking.model.plane.Seat;
import com.flightbooking.trash.passenger.Passenger;

import java.util.Scanner;

public class FlightBookingApplication {
    private static  Scanner keyboard = new Scanner(System.in);

  private static   Flight flight = new Flight();
    //flight.initializePlaneCabins();

    public static void printMen(){

        while(true) {
            System.out.println("******************************************************************");
            System.out.println();

            System.out.println("display plane seating chart     0");
            System.out.println("reserve booking (single)       1");
            System.out.println("reserve booking (mult) [BROKNE]      2");
            System.out.println("find booking by passport num booking      4");
            System.out.println("find booking by last name      5");
            System.out.println("calculate total flight revenueme      6");

            System.out.println("cancel booking  by passport    8");
            System.out.println("exit booking   app   9");
            System.out.println();
            System.out.println("******************************************************************");

            System.out.println();
            System.out.print("enter your menu selection: ");

            int menuSelection = Integer.parseInt(keyboard.next());
            System.out.println("menuSelection " + menuSelection);

            if (menuSelection ==0) {
flight.printSeatingChart();

            }else            if (menuSelection == 1) {
                createFlightBookingSingle();
            }else   if (menuSelection == 4) {

                System.out.println("find reserveation infoen...");
                System.out.println("enter passport num...");
                String passportNum = keyboard.next();
                System.out.println("passportNum " + passportNum);

                flight.displayPassengerInfoByPasspoerNumber(passportNum);

            }  else   if (menuSelection ==5) {

                    System.out.println("find reserveation infoe by last namen...");

                    System.out.println("enter last name...");

                    String lastName = keyboard.next();
                    System.out.println("lastName " + lastName);

                    flight.displayPassengerInfoByPassengerLastName(lastName);

            } else if (menuSelection == 6) {

                System.out.println("calulating revenue from lanae reserverationn...");

                //      System.out.println("enter last name...");

                //      String lastName = keyboard.next();
                //     System.out.println("lastName " + lastName);

                flight.calculateRevenueFromReservations();

            } else if (menuSelection == 8) {

System.out.println("cancel fligth screen...");
                System.out.println("enter passport num...");
                 String passportNum = keyboard.next();
                System.out.println("passportNum " + passportNum);

                flight.cancelFlight(passportNum);
            } else if (menuSelection == 9) {
                System.out.println("bye");
System.exit(0);
          }

        }
    }
    public static void main(String[] args) {

        printMen();

    }

    public static void createFlightBookingSingle(){

     //   System.out.println("welcome to the single flight booking");
boolean doWork=true;
        while (doWork) {
            PassengerClass passengerClass = flight.promptUserToSelectCabin();

//    System.out.println("passengerClass "+ passengerClass);

            flight.printSeatingChart(passengerClass);

            Seat userSeatSelection = flight.chooseSeatByTypeGivenClass(passengerClass);

            ////   System.out.println("userSeatSelection: " + userSeatSelection);

            Passenger passenger = new Passenger();

            passenger.createPassenger();
            //   System.out.println("passenger: " + passenger);

            //= flight.fetchSeat(passengerClass,)
            //   System.out.println("reserving seat with the passenger info +seat");
            //    System.out.println("seat: +seat");

            //   System.out.println("seat before register: " + userSeatSelection);

            userSeatSelection.setPassenger(passenger);
            userSeatSelection.setBooked(true);

            //   System.out.println("seat after register  " + userSeatSelection);

            //  System.out.println("seat map");
            //  flight.printSeatingChart();

            //first
            // System.out.println("seat: +seat");
            //    if (true) System.exit(0);   //


            //   --      display.printSeatMap(PassengerClass.FIRST);

            //  BookingManager bookingManager = new BookingManager(flight);

            System.out.print("Do you wish to reserve another seat? Select Yes (Y) or No (N): ");

            String menuSelection = keyboard.next().toUpperCase().trim();

            if(menuSelection.equals("Y")){doWork=true;}
                    else{doWork=false;System.out.println("no more adding...");}

            //System.out.println("menuSelection " + menuSelection);
        }
    }

}
