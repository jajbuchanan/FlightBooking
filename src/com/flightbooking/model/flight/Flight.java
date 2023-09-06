package com.flightbooking.model.flight;

import com.flightbooking.model.cabin.BusinessClassCabin;
import com.flightbooking.model.cabin.Cabin;
import com.flightbooking.model.cabin.FirstClassCabin;
import com.flightbooking.model.cabin.TravellerClassCabin;
import com.flightbooking.model.plane.PassengerClass;
import com.flightbooking.model.plane.Seat;
import com.flightbooking.model.plane.SeatType;
import com.flightbooking.trash.passenger.Passenger;

import java.util.*;

public class Flight {

    private final FirstClassCabin firstClassCabin;
    private final BusinessClassCabin businessClassCabin;
    private final TravellerClassCabin travellerClassCabin;
private Scanner keyboard = new Scanner(System.in);

    public Flight() {

        this.firstClassCabin = new FirstClassCabin();
        this.firstClassCabin.setLogicalRowNumberOffset(1);
        this.firstClassCabin.initializeSeats();

        this.businessClassCabin = new BusinessClassCabin();

        this.businessClassCabin.setLogicalRowNumberOffset(
                this.firstClassCabin.getLogicalRowNumberOffset()
                        + this.firstClassCabin.getNumberOfRows());

        this.businessClassCabin.initializeSeats();

        this.travellerClassCabin = new TravellerClassCabin();
        this.travellerClassCabin.setLogicalRowNumberOffset(
                this.businessClassCabin.getLogicalRowNumberOffset()
                        + this.businessClassCabin.getNumberOfRows());
        this.travellerClassCabin.initializeSeats();
      //  this.travellerClassCabin.setLogicalRowNumberOffset(this.firstClassCabin.getNumberOfRows() + this.businessClassCabin.getNumberOfRows());
    }



    public void printSeatingChart() {
        this.firstClassCabin.printSeatingChart();
        this.businessClassCabin.printSeatingChart();
        this.travellerClassCabin.printSeatingChart();

    }

    public void printSeatingChart(PassengerClass passengerClass) {
        switch (passengerClass) {
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
            System.out.println("passengerClass: " + passengerClass);

            System.out.println(" Enter the choice of seat type (window, standard, aisle) or seat number: ");

            userSeatSelection = keyboard.next().toUpperCase();

            System.out.println("userSeatSelection: " + userSeatSelection);

            if (userSeatSelection.matches("[A-Z]+")) {


                System.out.print("user wanted a type of seat, not specific seat");
                seat = fetchSeatByClassByType(passengerClass, userSeatSelection);

                System.out.println("returned seat: " + seat);
            }
            else {

                System.out.print("specific seat|1" +
                        "");

                boolean isIndividualSeatAvailable = this.isSpecificSeatAvailable(passengerClass, userSeatSelection);

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
            }
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

    private Seat fetchSeatByClassByType(PassengerClass passengerClass, String seatType) {

        if(passengerClass.equals(PassengerClass.FIRST)) {
            for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.firstClassCabin.seats[row][seatIndex];
                    //   System.out.println("checking: "+seat);

                    if (seat.isAvailable()&&seat.getType().toString().toUpperCase().equals(seatType.toUpperCase().trim())) {



                        System.out.println("found available seat matching seat type;");

//seat.setBooked(true);
                      //  seat.setPassenger(passenger);

                        return seat;
                    }

                }
            }
        }

        if(passengerClass.equals(PassengerClass.BUSINESS)) {
            for (int row = 0; row < this.businessClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.businessClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.businessClassCabin.seats[row][seatIndex];
                    //    System.out.println("checking: "+seat);

                    if (seat.isAvailable()&&seat.getType().toString().toUpperCase().equals(seatType.toUpperCase().trim())) {

                        System.out.println("found available seat;");

//seat.setBooked(true);
                      //  seat.setPassenger(passenger);

                        return seat;
                    }

                }
            }
        }

        if(passengerClass.equals(PassengerClass.TRAVELLER)) {
            for (int row = 0; row < this.travellerClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.travellerClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.travellerClassCabin.seats[row][seatIndex];
                    System.out.println("checking: "+seat);
                    if (seat.isAvailable()&&seat.getType().toString().toUpperCase().equals(seatType.toUpperCase().trim())) {

                        System.out.println("found available seat;");

                  //      seat.setBooked(true);
                //        seat.setPassenger(passenger);

                        return seat;
                    }

                }
            }
        }

        ////  System.out.println("found seat that was deleted:"+foundSeat);
        return null;
    }
    public Seat chooseSeatsForGroup(PassengerClass classSeletion, int  groupSize) {

        Seat seat = null;
        String userSeatSelection = null;

        List<Passenger> plist = new ArrayList();

        for(int i =0;i<groupSize;i++){

            if(i==0){
           Passenger pass = new Passenger();
                //
                System.out.print("Enter the 1st passenger’s first name: ");
                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("userSeatSelection: " + userSeatSelection);
pass.setFirstName(userSeatSelection);

//
                System.out.print("  Enter the 1st passenger’s last name: ");

                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("userSeatSelection: " + userSeatSelection);
                pass.setLastName(userSeatSelection);
              //

                       System.out.print("  Enter the 1st passport number: ");

                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("userSeatSelection: " + userSeatSelection);
                pass.setPassportNumber(userSeatSelection);
plist.add(pass);

            }else   if(i==2){
                Passenger pass = new Passenger();
                //
                System.out.print("Enter the 2nd passenger’s first name: ");
                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("userSeatSelection: " + userSeatSelection);
                pass.setFirstName(userSeatSelection);

//
                System.out.print("  Enter the 2nd passenger’s last name: ");

                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("userSeatSelection: " + userSeatSelection);
                pass.setLastName(userSeatSelection);
                //

                System.out.print("  Enter the 2nd passport number: ");

                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("userSeatSelection: " + userSeatSelection);
                pass.setPassportNumber(userSeatSelection);
                plist.add(pass);

            }else  if(i==3){
                Passenger pass = new Passenger();
                //
                System.out.print("Enter the 3rd passenger’s first name: ");
                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("userSeatSelection: " + userSeatSelection);
                pass.setFirstName(userSeatSelection);

//
                System.out.print("  Enter the 3rd passenger’s last name: ");

                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("userSeatSelection: " + userSeatSelection);
                pass.setLastName(userSeatSelection);
                //

                System.out.print("  Enter the 3rd passport number: ");

                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("userSeatSelection: " + userSeatSelection);
                pass.setPassportNumber(userSeatSelection);
                plist.add(pass);

            }

        }

        for(Passenger passenger:plist){
            reserveFirstAvailableSeatInClass(classSeletion,passenger);}

        return seat;

    }

    private Seat reserveFirstAvailableSeatInClass(PassengerClass passengerClass, Passenger passenger ) {

        if(passengerClass.equals(PassengerClass.FIRST)) {
            for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.firstClassCabin.seats[row][seatIndex];
                    //   System.out.println("checking: "+seat);

                    if (seat.isAvailable()) {

                        System.out.println("found available seat;");

                        seat.setBooked(true);
                        seat.setPassenger(passenger);

                      return seat;
                    }

                }
            }
        }

        if(passengerClass.equals(PassengerClass.BUSINESS)) {
            for (int row = 0; row < this.businessClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.businessClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.businessClassCabin.seats[row][seatIndex];
                //    System.out.println("checking: "+seat);

                    if (seat.isAvailable()) {

                        System.out.println("found available seat;");

                        seat.setBooked(true);
                        seat.setPassenger(passenger);

                        return seat;
                    }

                }
            }
        }

        if(passengerClass.equals(PassengerClass.TRAVELLER)) {
            for (int row = 0; row < this.travellerClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.travellerClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.travellerClassCabin.seats[row][seatIndex];
                    System.out.println("checking: "+seat);
                    if (seat.isAvailable()) {

                        System.out.println("found available seat;");

                        seat.setBooked(true);
                        seat.setPassenger(passenger);

                        return seat;
                    }

                }
            }
        }

      ////  System.out.println("found seat that was deleted:"+foundSeat);
        return null;
    }



        public Seat fetchSeat(PassengerClass passengerClass, String seatSelection) {

        System.out.println(" enter ::  fetchSeat()");

        int rowNumber = Integer.parseInt(seatSelection.replaceAll("[A-Z]+", ""));//

        char seatLetter = seatSelection.replaceAll("[0-9]+", "").charAt(0);

        System.out.println("rowNumber: " + rowNumber);


        int logicalSeatRow=-1;


        System.out.println("raw this.firstClassCabin.logicalRowNumberOffset: " + this.firstClassCabin.logicalRowNumberOffset);
        System.out.println("raw this.businessClassCabin.logicalRowNumberOffset;: " +this.businessClassCabin.logicalRowNumberOffset);

        System.out.println("raw this.travellerClassCabin.logicalRowNumberOffse: " +this.travellerClassCabin.logicalRowNumberOffset);

        switch (passengerClass) {
            case FIRST ->logicalSeatRow=rowNumber- this.firstClassCabin.logicalRowNumberOffset;
            case BUSINESS ->logicalSeatRow=rowNumber- this.businessClassCabin.logicalRowNumberOffset;
            case TRAVELLER -> logicalSeatRow=rowNumber- this.travellerClassCabin.logicalRowNumberOffset;
        }

    //    logicalSeatRow = rowNumber;
        System.out.println("logicalSeatRow: " + logicalSeatRow);

        System.out.println("seatLetter: " + seatLetter);

        Seat specificSeat = this.fetchSeatByClassRowAndLetter(passengerClass, logicalSeatRow, seatLetter);
       // tr
        System.out.println("specificSeat fetchSeatByClassRowAndLetter: " + specificSeat);

        return specificSeat;

    }

    public Seat fetchSeatByClassRowAndLetter(PassengerClass passengerClass, int logicalSeatRow, char seatLetter) {
        System.out.println("enter // fetchSeatByClassRowAndLetter");



        System.out.println("logicalSeatRow!!!: " + logicalSeatRow);

        switch (passengerClass) {

            case FIRST:

                System.out.println("here");


     //           int adjustedRow = logicalSeatRow - this.firstClassCabin.getLogicalRowNumberOffset();

            //    System.out.println("logicalSeatRow: " + logicalSeatRow);

            //    System.out.println("adjustedRow" + adjustedRow);

                //    for(){

                //    }
                Seat seat = firstClassCabin.seats[logicalSeatRow][firstClassCabin.deriveSeatNumberForSeatLater(seatLetter)];

                System.out.println("seat from the fetch" + seat);

                return seat;
            case BUSINESS:

                System.out.println("BUSINESS BUSINESS BUSINESS");

                System.out.println("logicalSeatRow!!!: " + logicalSeatRow);

                //           int adjustedRow = logicalSeatRow - this.firstClassCabin.getLogicalRowNumberOffset();

                //    System.out.println("logicalSeatRow: " + logicalSeatRow);

                //    System.out.println("adjustedRow" + adjustedRow);

                //    for(){

                //    }
                 seat = this.businessClassCabin.seats[logicalSeatRow][businessClassCabin.deriveSeatNumberForSeatLater(seatLetter)];

                System.out.println("seat from the fetch" + seat);

                return seat;

            case TRAVELLER:

                System.out.println("TRAVELLER TRAVELLER TRAVELLER");


                //           int adjustedRow = logicalSeatRow - this.firstClassCabin.getLogicalRowNumberOffset();

                //    System.out.println("logicalSeatRow: " + logicalSeatRow);

                //    System.out.println("adjustedRow" + adjustedRow);

                //    for(){

                //    }
                seat = this.travellerClassCabin.seats[logicalSeatRow][travellerClassCabin.deriveSeatNumberForSeatLater(seatLetter)];

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

    private void bookSeatOld(
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

        System.out.println("raw rowNumber: " + rowNumber);

        int adjustedrownum=-1;


        System.out.println("raw this.firstClassCabin.logicalRowNumberOffset: " + this.firstClassCabin.logicalRowNumberOffset);
        System.out.println("raw this.businessClassCabin.logicalRowNumberOffset;: " +this.businessClassCabin.logicalRowNumberOffset);

        System.out.println("raw this.travellerClassCabin.logicalRowNumberOffse: " +this.travellerClassCabin.logicalRowNumberOffset);

        switch (passengerClass) {
            case FIRST ->adjustedrownum=rowNumber- this.firstClassCabin.logicalRowNumberOffset;
            case BUSINESS ->adjustedrownum=rowNumber- this.businessClassCabin.logicalRowNumberOffset;
            case TRAVELLER -> adjustedrownum=rowNumber- this.travellerClassCabin.logicalRowNumberOffset;
        }

        System.out.println("adjustedrownum: " + adjustedrownum);

        System.out.println("seatLetter: " + seatLetter);

        Seat specificSeat = this.fetchSeatByClassRowAndLetter(passengerClass, adjustedrownum, seatLetter);
        System.out.println("specificSeat: " + specificSeat);

        return specificSeat.isAvailable();

    }

    public PassengerClass promptUserToSelectCabin() {

        System.out.println("Enter the cabin class (First, Business, Traveller): ");

        String classChoice = keyboard.next().toUpperCase();

        System.out.println("choice" + classChoice);

        try {

            PassengerClass chosenClass = PassengerClass.valueOf(classChoice);

            System.out.println("chosenClass: " + chosenClass);


            return chosenClass;
            //       THIS.Seat[][] seats = flight.displayClassSeats(PassengerClass.valueOf(classChoice));
            //       int startRow = flight.getClassCabin(chosenClass).getStartRow();
            //    printSeatMap(chosenClass, seats, startRow);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter a valid class.");
            return null;
        }
    }
public Seat cancelFlight(String passortNumber) {

    System.out.println("cancelFlight // enter ");

    System.out.println("deleting a reservation w/ passport number" + passortNumber);

    Seat foundSeat = null;

    for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
        for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

            Seat seat = this.firstClassCabin.seats[row][seatIndex];
            System.out.println("checking: "+seat);

            if (seat.isBooked() && Objects.equals(seat.getPassenger().getPassportNumber(), passortNumber)) {

                System.out.println("found seat matching passport;");

                seat.setBooked(false);
                seat.setPassenger(null);

                foundSeat = seat;
            }

        }
    }

    for (int row = 0; row < this.businessClassCabin.getNumberOfRows(); row++) {
        for (int seatIndex = 0; seatIndex < this.businessClassCabin.getSeatLetters().length; seatIndex++) {

            Seat seat = this.businessClassCabin.seats[row][seatIndex];
            System.out.println("checking: "+seat);

            if (seat.isBooked() && Objects.equals(seat.getPassenger().getPassportNumber(), passortNumber)) {

                System.out.println("eat.isBooked(): "+seat.isBooked());


                System.out.println("seat.getPassenger().getPassportNumber(): "+seat.getPassenger().getPassportNumber());
                System.out.println("passortNumber"+seat);

                System.out.println("found seat matching passport;");

                seat.setBooked(false);
                seat.setPassenger(null);

                foundSeat = seat;
            }

        }
    }

    for (int row = 0; row < this.travellerClassCabin.getNumberOfRows(); row++) {
        for (int seatIndex = 0; seatIndex < this.travellerClassCabin.getSeatLetters().length; seatIndex++) {

            Seat seat = this.travellerClassCabin.seats[row][seatIndex];
            System.out.println("checking: "+seat);
            if (seat.isBooked() && Objects.equals(seat.getPassenger().getPassportNumber(), passortNumber)) {

                System.out.println("found seat matching passport;");

                seat.setBooked(false);
                seat.setPassenger(null);

                foundSeat = seat;
            }

        }
    }

    System.out.println("found seat that was deleted:"+foundSeat);
    return foundSeat;
}

    public Seat displayPassengerInfoByPasspoerNumber(String passortNumber) {

        System.out.println("displayPassengerInfoByPasspoerNumber // enter ");

        //    System.out.println("displayPassengerInfoByPasspoerNumber a reserveration w/ passort number" + passortNumber);

        Seat foundSeat = null;

        for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.firstClassCabin.seats[row][seatIndex];
                //       System.out.println("checking: "+seat);

                if (seat.isBooked() && Objects.equals(seat.getPassenger().getPassportNumber(), passortNumber)) {

                    System.out.println("found seat matching passport;");

                    //        seat.setBooked(false);
                    //      seat.setPassenger(null);

                    foundSeat = seat;
                }

            }
        }

        for (int row = 0; row < this.businessClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.businessClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.businessClassCabin.seats[row][seatIndex];
                //     System.out.println("checking: "+seat);

                if (seat.isBooked() && Objects.equals(seat.getPassenger().getPassportNumber(), passortNumber)) {

                    //    System.out.println("eat.isBooked(): "+seat.isBooked());


                    //     System.out.println("seat.getPassenger().getPassportNumber(): "+seat.getPassenger().getPassportNumber());
                    //    System.out.println("passortNumber"+seat);

                    //    System.out.println("found seat matching passport;");

                    //     seat.setBooked(false);
                    //     seat.setPassenger(null);

                    foundSeat = seat;
                }

            }
        }

        for (int row = 0; row < this.travellerClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.travellerClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.travellerClassCabin.seats[row][seatIndex];
                //   System.out.println("checking: "+seat);
                if (seat.isBooked() && Objects.equals(seat.getPassenger().getPassportNumber(), passortNumber)) {

                    //     System.out.println("found seat matching passport;");

                    //     seat.setBooked(false);
                    //     seat.setPassenger(null);

                    foundSeat = seat;
                }

            }
        }

        //    System.out.println("found seat that was deleted:"+foundSeat);

        if(foundSeat!=null) {

        System.out.println("First name\tLast name\tPassport number\tSeat reserved\tClass\tType");
//First name	Last name	Passport number	Seat reserved	Class	Type
//Akram	Khan	JJO3412	24A	Traveller	Window
        System.out.print(foundSeat.getPassenger().getFirstName());
        System.out.print("\t");

        System.out.print(foundSeat.getPassenger().getLastName());
        System.out.print("\t");

        System.out.print(foundSeat.getPassenger().getPassportNumber());
        System.out.print("\t");

        System.out.print(foundSeat.getSeatNumber());
        System.out.print("\t");
        System.out.print(foundSeat.getPassengerClass());
        System.out.print("\t");

        System.out.println(foundSeat.getType());
        System.out.print("\t");
    }




        return foundSeat;
    }

    public Seat displayPassengerInfoByPassengerLastName(String lastName) {
      //  public Seat displayPassengerInfoByPasspoerNumber(String passortNumber) {

            System.out.println("displayPassengerInfoByPasspoerNumber // enter ");

            //    System.out.println("displayPassengerInfoByPasspoerNumber a reserveration w/ passort number" + passortNumber);

            Seat foundSeat = null;

            for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.firstClassCabin.seats[row][seatIndex];
                    //       System.out.println("checking: "+seat);

                        if (seat.isBooked() && Objects.equals(seat.getPassenger().getLastName().toUpperCase().trim(),
                                lastName.toUpperCase().trim()

                        )) {

                            System.out.println("found seat matching last name;");

                        //        seat.setBooked(false);
                        //      seat.setPassenger(null);

                        foundSeat = seat;
                    }

                }
            }

            for (int row = 0; row < this.businessClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.businessClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.businessClassCabin.seats[row][seatIndex];
                    //     System.out.println("checking: "+seat);
                    if (seat.isBooked() && Objects.equals(seat.getPassenger().getLastName().toUpperCase().trim(),
                            lastName.toUpperCase().trim()

                    )) {

                        System.out.println("found seat matching last name;");
                        foundSeat = seat;
                    }

                }
            }

            for (int row = 0; row < this.travellerClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.travellerClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.travellerClassCabin.seats[row][seatIndex];
                    if (seat.isBooked() && Objects.equals(seat.getPassenger().getLastName().toUpperCase().trim(),
                            lastName.toUpperCase().trim()

                    )) {

                        System.out.println("found seat matching last name;");
                        foundSeat = seat;
                    }

                }
            }

            //    System.out.println("found seat that was deleted:"+foundSeat);

        if(foundSeat!=null) {
            System.out.println("First name\tLast name\tPassport number\tSeat reserved\tClass\tType");
//First name	Last name	Passport number	Seat reserved	Class	Type
//Akram	Khan	JJO3412	24A	Traveller	Window
            System.out.print(foundSeat.getPassenger().getFirstName());
            System.out.print("\t");

            System.out.print(foundSeat.getPassenger().getLastName());
            System.out.print("\t");

            System.out.print(foundSeat.getPassenger().getPassportNumber());
            System.out.print("\t");

            System.out.print(foundSeat.getSeatNumber());
            System.out.print("\t");
            System.out.print(foundSeat.getPassengerClass());
            System.out.print("\t");

            System.out.println(foundSeat.getType());
            System.out.print("\t");

        }



            return foundSeat;
        }

    public void calculateRevenueFromReservations() {
        //  public Seat displayPassengerInfoByPasspoerNumber(String passortNumber) {

        //   System.out.println("calculateRevenueFromReservations // enter ");

        int firstClassCounter = 0;
        int businesslassCounter = 0;
        int travellerClassCounter = 0;

        for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.firstClassCabin.seats[row][seatIndex];

                if (seat.isBooked()) {

                    System.out.println("found booked seat");

                    firstClassCounter++;

                }

            }
        }

        for (int row = 0; row < this.businessClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.businessClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.businessClassCabin.seats[row][seatIndex];

                if (seat.isBooked()) {

                    System.out.println("found booked seat");

                    businesslassCounter++;

                }

            }
        }

        for (int row = 0; row < this.travellerClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.travellerClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.travellerClassCabin.seats[row][seatIndex];

                if (seat.isBooked()) {

                    System.out.println("found booked seat");

                    travellerClassCounter++;

                }

            }
        }

        System.out.println();
        System.out.println("Class\tQuantity\tPrice");
        System.out.println();

        System.out.println("First\t\t" +firstClassCounter +"\t" + firstClassCounter * 1800);
        System.out.println("Business\t\t"+ businesslassCounter +"\t" +  businesslassCounter * 1200);
        System.out.println("Traveller\t" + + travellerClassCounter +"\t" + + travellerClassCounter * 1000);


        System.out.println();


    }
    public void generateReportOfUserReservation() {
        //  public Seat displayPassengerInfoByPasspoerNumber(String passortNumber) {

        //   System.out.println("calculateRevenueFromReservations // enter ");



        List<Seat> seatList = new ArrayList<Seat>();

        for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.firstClassCabin.seats[row][seatIndex];

                if (seat.isBooked()) {

                    System.out.println("found booked seat");

                  seatList.add(seat);

                }

            }
        }

        for (int row = 0; row < this.businessClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.businessClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.businessClassCabin.seats[row][seatIndex];

                if (seat.isBooked()) {

                    System.out.println("found booked seat");

                    seatList.add(seat);

                }

            }
        }

        for (int row = 0; row < this.travellerClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.travellerClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.travellerClassCabin.seats[row][seatIndex];
                if (seat.isBooked()) {

                    System.out.println("found booked seat");

                    seatList.add(seat);

                }

            }
        }

      //  System.out.println(seatList);

        Collections.sort(seatList);

   //     System.out.println(seatList);

        //Collections.sor(seatList);

    //    System.out.println(seatList);

      //  System.out.println("Class\tQuantity\tPrice");
     //   System.out.println();

      //  System.out.println("First\t\t" +firstClassCounter +"\t" + firstClassCounter * 1800);
      //  System.out.println("Business\t\t"+ businesslassCounter +"\t" +  businesslassCounter * 1200);
    //    System.out.println("Traveller\t" + + travellerClassCounter +"\t" + + travellerClassCounter * 1000);


      //  System.out.println();
    //    Table 11. Sample report – passenger data sorted alphabetically by last name
      System.out.println("  First name	Last name	Passport number	Seat reserved	Class	Type");

      for(Seat seat:seatList) {

          System.out.print(seat.getPassenger().getFirstName());System.out.print("\t");
          System.out.print(seat.getPassenger().getLastName());System.out.print("\t");
          System.out.print(seat.getPassenger().getPassportNumber());System.out.print("\t");
          System.out.print(seat.getSeatNumber());System.out.print("\t");
          System.out.print(seat.getPassengerClass());System.out.print("\t");
          System.out.print(seat.getType());System.out.print("\t");

          System.out.println("");

         // System.out.print("\t");



      }
    }
}
