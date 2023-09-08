import com.flightbooking.model.flight.Flight;
import com.flightbooking.model.plane.PassengerClass;
import com.flightbooking.model.plane.Seat;
import com.flightbooking.model.passenger.Passenger;

import java.util.Scanner;

public class FlightBookingApplication {
    private static  Scanner keyboard = new Scanner(System.in);

  private static   Flight flight = new Flight();
    //flight.initializePlaneCabins();

    public static void printUserActionMenu(){

        while(true) {

            System.out.println("******************************************************************");
            System.out.println();

            System.out.println("display plane seating chart     0");
            System.out.println("reserve booking (single)       1");
            System.out.println("reserve booking (multiple)           2");
            System.out.println("find booking by passport num booking      4");
            System.out.println("find booking by last name      5");
            System.out.println("calculate total flight revenue      6");
            System.out.println("generate report of reservations and users   7");
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
           }else            if (menuSelection == 2) {
                createFlightBookingGroup();
        }else   if (menuSelection == 4) {

                System.out.println("find reservation information");
                System.out.println("enter passport number");
                String passportNum = keyboard.next();
                System.out.println("passportNum " + passportNum);

                flight.displayPassengerInfoByPasspoerNumber(passportNum);

            }  else   if (menuSelection ==5) {

                    System.out.println("find reservation information by last name...");

                    System.out.println("enter last name...");

                    String lastName = keyboard.next();
                    System.out.println("lastName " + lastName);

                    flight.displayPassengerInfoByPassengerLastName(lastName);

            } else if (menuSelection == 6) {

                System.out.println("calculating revenue from lanae reservation...");

                //      System.out.println("enter last name...");

                //      String lastName = keyboard.next();
                //     System.out.println("lastName " + lastName);

                flight.calculateRevenueFromReservations();

            } else if (menuSelection == 7) {

            System.out.println("creating user report");

            //      System.out.println("enter last name...");

            //      String lastName = keyboard.next();
            //     System.out.println("lastName " + lastName);

            flight.generateReportOfUserReservation();

        } else if (menuSelection == 8) {

System.out.println("cancel flight screen...");
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

     printUserActionMenu();
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
    public static void createFlightBookingGroup(){

           System.out.println("createFlightBookingGroup");

/*
 Enter the number of seats required together: 3
 Enter the choice of class (Business, Traveller, First): Business
  Enter the 1st passenger’s first name: Ali
  Enter the 1st passenger’s last name: Aslam
  Enter the 1st passport number: AED1021
  Enter the 2nd passenger’s first name: Jordan
  Enter the 2nd passenger’s last name: Robinson
  Enter the 2nd passport number: GHJ2101
  Enter the 3rd passenger’s first name: Daniel
  Enter the 3rd passenger’s last name: Scott
  Enter the 3rd passport number: AED4301

 */        System.out.print(" Enter the number of seats required together :");

        int numberSeatsBook = Integer.parseInt(keyboard.next());//needs validation fot be more than 0 and not more than 3;
        System.out.println("menuSelection " + numberSeatsBook);

        System.out.print("  Enter the choice of class (Business, Traveller, First): ");

        PassengerClass passengerClass = flight.promptUserToSelectCabin();
        //String classel=keyboard.next();
        //System.out.println("classel " + classel);

        flight.chooseSeatsForGroup(passengerClass,numberSeatsBook);
        boolean doWork=true;

   for(int i =0;i<numberSeatsBook;i++){


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
            else{doWork=false;System.out.println("no more passengers to add...");}

            //System.out.println("menuSelection " + menuSelection);
        }
    }

}
