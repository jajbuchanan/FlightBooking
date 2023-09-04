package com.flightbooking.model.plane;

import com.flightbooking.model.cabin.BusinessClassCabin;
import com.flightbooking.model.cabin.Cabin;
import com.flightbooking.model.cabin.FirstClassCabin;
import com.flightbooking.model.cabin.TravellerClassCabin;
import com.flightbooking.passenger.Passenger;

public class Flight {
    private final FirstClassCabin firstClassCabin;

    private final BusinessClassCabin businessClassCabin;

    private final TravellerClassCabin travellerClassCabin;

    public Flight() {
        this.firstClassCabin = new FirstClassCabin(1);
        this.businessClassCabin = new BusinessClassCabin(
                firstClassCabin.getNumberOfRows() + 1);
        this.travellerClassCabin = new TravellerClassCabin(
                firstClassCabin.getNumberOfRows()
                        + businessClassCabin.getNumberOfRows() + 1);
    }

    public void initialize() {
        // initialise all cabins
        firstClassCabin.initializeSeats("first");
        businessClassCabin.initializeSeats("business");
        travellerClassCabin.initializeSeats("traveller");
    }

    // print all cabin information
    public void printAllCabins() {
        System.out.println("First Class Cabin:");
        firstClassCabin.cabinSeatInfo();

        System.out.println("\nBusiness Class Cabin:");
        businessClassCabin.cabinSeatInfo();

        System.out.println("\nTraveller Class Cabin:");
        travellerClassCabin.cabinSeatInfo();
    }

    // define seat booking functions

    public void bookSeat(
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
    public char[] classSeatLetters(PassengerClass cabinType) {
        return switch (cabinType) {
            case FIRST -> firstClassCabin.seatLetters;
            case BUSINESS -> businessClassCabin.seatLetters;
            case TRAVELLER -> travellerClassCabin.seatLetters;
        };
    }


    public void displayClassSeats(PassengerClass pClass) {
        switch (pClass) {
            case FIRST -> this.firstClassCabin.printSeatingChart();
            case BUSINESS -> this.businessClassCabin.printSeatingChart();
            case TRAVELLER -> this.travellerClassCabin.printSeatingChart();
        }
        ;
    }

    private void displayseatsbytypebyclass(PassengerClass pClass,
                                          SeatType seatType ) {
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
    public Seat fetchSeatByClassRowAndLetter(PassengerClass passengerClass, int seatRow, char seatLetter) {

        switch (passengerClass) {

            case FIRST :

                return  firstClassCabin.seats[seatRow][firstClassCabin.getSeatLetterIndex(seatLetter)];

                        //].seatInfo();
                //> firstClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();

            //    break;
   //         case BUSINESS -> businessClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();
       //     case TRAVELLER -> travellerClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();
            default:return null;   }
    }
    public boolean isSpecificSeatAvailable(PassengerClass passengerClass, String seatSelection){

        System.out.println(" enter ::  isSeatAvailable()");


//boolean isAvailable =

        int rowNumber= Integer.parseInt(seatSelection.replaceAll("[A-Z]+",""));//
        char seatLetter= seatSelection.replaceAll("[0-9]+","").charAt(0);

        System.out.println("rowNumber: " + rowNumber);

        System.out.println("seatLetter: " + seatLetter);

        Seat specificSeat = this.fetchSeatByClassRowAndLetter(passengerClass,rowNumber,seatLetter);
        System.out.println("specificSeat: " + specificSeat);

        return specificSeat.isAvailable();
/*
    System.out.println("seat nuumber selection: "+split);
        switch (passengerClass) {
            case FIRST -> firstClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();
            case BUSINESS -> businessClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();
            case TRAVELLER -> travellerClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();
        }

        System.out.println("checking isf this seat is available: " + seat);


        this.*/
//return false;
    }

    //public boolean bookAny
}