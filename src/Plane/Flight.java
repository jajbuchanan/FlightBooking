package Plane;
import Passenger.*;

public class Flight {
    public final FirstClassCabin firstClassCabin;
    public final BusinessClassCabin businessClassCabin;
    public final TravellerClassCabin travellerClassCabin;

    public Flight() {
        this.firstClassCabin = new FirstClassCabin(1);
        this.businessClassCabin = new BusinessClassCabin(
                firstClassCabin.numRows + 1);
        this.travellerClassCabin = new TravellerClassCabin(
                firstClassCabin.numRows
                + businessClassCabin.numRows + 1);
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
            case FIRST -> firstClassCabin.numRows;
            case BUSINESS -> businessClassCabin.numRows;
            case TRAVELLER -> travellerClassCabin.numRows;
            default -> throw new IllegalArgumentException("Unknown cabin type: " + cabinType);
        };
    }

    // define getters for the cabins' rowLetters
    public char[] classSeatLetters(PassengerClass cabinType) {
        return switch (cabinType) {
            case FIRST -> firstClassCabin.seatLetters;
            case BUSINESS -> businessClassCabin.seatLetters;
            case TRAVELLER -> travellerClassCabin.seatLetters;
            default -> throw new IllegalArgumentException("Unknown cabin type: " + cabinType);
        };
    }

    // return the index of the seat letter from the list of letters in each cabin class
    @SuppressWarnings("null")
    public int classSeatLetterIndex(PassengerClass passengerClass, char seatLetter) {
        return switch (passengerClass) {
            case FIRST -> firstClassCabin.getSeatLetterIndex(seatLetter);
            case BUSINESS -> businessClassCabin.getSeatLetterIndex(seatLetter);
            case TRAVELLER -> travellerClassCabin.getSeatLetterIndex(seatLetter);
            default -> (Integer) null;
        };
    }

    public Seat[][] displayClassSeats(PassengerClass pClass) {
        return switch (pClass) {
            case FIRST -> firstClassCabin.seats;
            case BUSINESS -> businessClassCabin.seats;
            case TRAVELLER -> travellerClassCabin.seats;
            default -> throw new IllegalArgumentException("Illegal class type.");
        };
    }

    public Cabin getClassCabin(PassengerClass pClass) {
        return switch (pClass) {
            case FIRST -> firstClassCabin;
            case BUSINESS -> businessClassCabin;
            case TRAVELLER -> travellerClassCabin;
        };
    }

    public void getSeatInfo(PassengerClass passengerClass, int relativeRow, int seatLetterIndex) {
        switch (passengerClass) {
            case FIRST:
                firstClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();
                break;
            case BUSINESS:
                businessClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();
                break;
            case TRAVELLER:
                travellerClassCabin.seats[relativeRow][seatLetterIndex].seatInfo();
                break;
        }
    }

}
