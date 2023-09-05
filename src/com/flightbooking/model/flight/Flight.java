package com.flightbooking.model.flight;

import com.flightbooking.model.cabin.BusinessClassCabin;
import com.flightbooking.model.cabin.Cabin;
import com.flightbooking.model.cabin.FirstClassCabin;
import com.flightbooking.model.cabin.TravellerClassCabin;
import com.flightbooking.model.plane.PassengerClass;
import com.flightbooking.model.plane.Seat;
import com.flightbooking.model.plane.SeatType;
import com.flightbooking.trash.passenger.Passenger;

import java.util.Scanner;

public class Flight {

    private final FirstClassCabin firstClassCabin;
    private final BusinessClassCabin businessClassCabin;
    private final TravellerClassCabin travellerClassCabin;
    private Scanner keyboard = new Scanner(System.in);

    public Flight() {
        this.firstClassCabin = new FirstClassCabin();
        this.businessClassCabin = new BusinessClassCabin();
        this.travellerClassCabin = new TravellerClassCabin();
    }

    public void initialize() {
        // initialise all cabins
        firstClassCabin.initializeSeats("first");
        businessClassCabin.initializeSeats("business");
        travellerClassCabin.initializeSeats("traveller");
    }

    public void displayClassSeats() {
        this.firstClassCabin.printSeatingChart();
        this.businessClassCabin.printSeatingChart();
        this.travellerClassCabin.printSeatingChart();

    }

    public void displayClassSeats(PassengerClass pClass) {
        switch (pClass) {
            case FIRST -> this.firstClassCabin.printSeatingChart();
            case BUSINESS -> this.businessClassCabin.printSeatingChart();
            case TRAVELLER -> this.travellerClassCabin.printSeatingChart();
        }
    }


    public Seat chooseSeatByTypeGivenClass(PassengerClass passengerClass) {

        Seat seat = null;
        String userSeatSelection = null;

        try {
//
            System.out.println("chosenClass: " + passengerClass);


            System.out.println(" Enter the choice of seat type (window, standard, aisle) or seat number: ");

            userSeatSelection = keyboard.next().toUpperCase();

            System.out.println("userSeatSelection: " + userSeatSelection);

            boolean isIndividualSeatAvailable = isSpecificSeatAvailable(passengerClass, userSeatSelection);

            System.out.println("is isIndividualSeatAvailable: + " + isIndividualSeatAvailable);


            if (userSeatSelection.matches("[0-9A-Z]+") && !isIndividualSeatAvailable) {

                System.out.println("user wanted a specific seat, but not available: + ");

                System.out.println("choose a specific seat again");

                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("user seat choice " + userSeatSelection);

                seat = fetchSeat(passengerClass, userSeatSelection);

                System.out.println("returned seat: " + seat);

                isIndividualSeatAvailable = seat.isAvailable();
            }


            System.out.println("user seat choice " + userSeatSelection);

            seat = fetchSeat(passengerClass, userSeatSelection);

            System.out.println("returned seat: " + seat);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter a valid class.");
        }

        return seat;
        // /./first
        //  SeatType seatType = SeatType.valueOf(userSeatSelection);

        //   System.out.println("seatType" + seatType);

        //   System.out.println("priting class sets for this flight");

//      flight.displayClassSeats(seatType);
        ////        //   PassengerClass chosenClass = PassengerClass.valueOf(userSeatSelection);


        //      this.flight.displayseatsbytypebyclass(chosenClass,seatType);


        //       THIS.Seat[][] seats = flight.displayClassSeats(PassengerClass.valueOf(userSeatSelection));
        //       int startRow = flight.getClassCabin(chosenClass).getStartRow();
        //    printSeatMap(chosenClass, seats, startRow);

    }

    public Seat fetchSeat(PassengerClass passengerClass, String seatSelection) {

        System.out.println(" enter ::  fetchSeat()");

        int rowNumber = Integer.parseInt(seatSelection.replaceAll("[A-Z]+", ""));//

        char seatLetter = seatSelection.replaceAll("[0-9]+", "").charAt(0);

        System.out.println("rowNumber: " + rowNumber);

        System.out.println("seatLetter: " + seatLetter);

        Seat specificSeat = this.fetchSeatByClassRowAndLetter(passengerClass, rowNumber, seatLetter);
        System.out.println("specificSeat fetchSeatByClassRowAndLetter: " + specificSeat);

        return specificSeat;

    }

    public Seat fetchSeatByClassRowAndLetter(PassengerClass passengerClass, int seatRow, char seatLetter) {
        System.out.println("enter // fetchSeatByClassRowAndLetter");

        switch (passengerClass) {

            case FIRST:

                System.out.println("here");

                Seat seat =
                        firstClassCabin.seats[seatRow][firstClassCabin.getSeatLetterIndex(seatLetter)];

                System.out.println("seat from the fetch" + seat);
                return seat;

            //].seatInfo();
            //> firstClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();

            //    break;
            //         case BUSINESS -> businessClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();
            //     case TRAVELLER -> travellerClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();
            //     break;

            default:

                return null;
        }
        //Fi  System.out.println("enter // fetchSeatByClassRowAndLetter");
    }

    // define seat booking functions

    private void bookSedddat(
            PassengerClass passengerClass,
            int relativeRowNum,
            int seatLetterIndex,
            Passenger passenger) {
        switch (passengerClass) {
            case FIRST: {
                // firstClassCabin.seats[relativeRowNum][seatLetterIndex].bookSeat(passenger);
                firstClassCabin.seats[relativeRowNum][seatLetterIndex].setPassenger(passenger);
                break;
            }
            case BUSINESS: {
                //  businessClassCabin.seats[relativeRowNum][seatLetterIndex].bookSeat(passenger);
                businessClassCabin.seats[relativeRowNum][seatLetterIndex].setPassenger(passenger);
                break;
            }
            case TRAVELLER: {
                //   travellerClassCabin.seats[relativeRowNum][seatLetterIndex].bookSeat(passenger);
                travellerClassCabin.seats[relativeRowNum][seatLetterIndex].setPassenger(passenger);
                break;
            }
        }
    }

    // define getters for the cabins' numRows
    public int getClassRows(PassengerClass cabinType) {
        return switch (cabinType) {
            case FIRST -> firstClassCabin.getNumberOfRows();
            case BUSINESS -> businessClassCabin.getNumberOfRows();
            case TRAVELLER -> travellerClassCabin.getNumberOfRows();
        };
    }

    // define getters for the cabins' rowLetters
    public char[] lookupSeatLettersByCabinType(PassengerClass passengerClass) {
        return switch (passengerClass) {
            case FIRST -> firstClassCabin.getSeatLetters();
            case BUSINESS -> businessClassCabin.getSeatLetters();
            case TRAVELLER -> travellerClassCabin.getSeatLetters();
        };
    }


    private void displayseatsbytypebyclass(PassengerClass pClass,
                                           SeatType seatType) {
        switch (pClass) {
            case FIRST -> this.firstClassCabin.printSeatsBySeatType(seatType);
            //     case BUSINESS -> this.businessClassCabin.printSeatingChart();
            //         case TRAVELLER -> this.travellerClassCabin.printSeatingChart();
        }
        ;
    }

    public Cabin getClassCabin(PassengerClass pClass) {
        return switch (pClass) {
            case FIRST -> firstClassCabin;
            case BUSINESS -> businessClassCabin;
            case TRAVELLER -> travellerClassCabin;
        };
    }


    public void printSeatingMap() {

        System.out.println("printing the seat chart for the entire flight");

        this.firstClassCabin.printSeatingChart();

        this.businessClassCabin.printSeatingChart();

        this.travellerClassCabin.printSeatingChart();


    }

    public boolean isSpecificSeatAvailable(PassengerClass passengerClass, String seatSelection) {

        System.out.println(" enter ::  isSeatAvailable()");

        int rowNumber = Integer.parseInt(seatSelection.replaceAll("[A-Z]+", ""));//
        char seatLetter = seatSelection.replaceAll("[0-9]+", "").charAt(0);

        System.out.println("rowNumber: " + rowNumber);

        System.out.println("seatLetter: " + seatLetter);

        Seat specificSeat = this.fetchSeatByClassRowAndLetter(passengerClass, rowNumber, seatLetter);
        System.out.println("specificSeat: " + specificSeat);

        return specificSeat.isAvailable();

    }

    public PassengerClass promptUserToSeletCabin() {
        System.out.println("Enter the cabin class (First, Business, Traveller): ");
        String classChoice = keyboard.next().toUpperCase();
        System.out.println("choice" + classChoice);

        try {

            PassengerClass chosenClass = PassengerClass.valueOf(classChoice);

            System.out.println("chosenClass: " + chosenClass);

            this.displayClassSeats(chosenClass);

            return chosenClass;
            //       THIS.Seat[][] seats = flight.displayClassSeats(PassengerClass.valueOf(classChoice));
            //       int startRow = flight.getClassCabin(chosenClass).getStartRow();
            //    printSeatMap(chosenClass, seats, startRow);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter a valid class.");
            return null;
        }
    }

}
