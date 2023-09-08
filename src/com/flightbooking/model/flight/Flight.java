package com.flightbooking.model.flight;

import com.flightbooking.model.cabin.BusinessClassCabin;
import com.flightbooking.model.cabin.FirstClassCabin;
import com.flightbooking.model.cabin.TravellerClassCabin;
import com.flightbooking.model.plane.PassengerClass;
import com.flightbooking.model.plane.Seat;
import com.flightbooking.model.passenger.Passenger;

import java.util.*;

public class Flight {

    private final FirstClassCabin firstClassCabin;
    private final BusinessClassCabin businessClassCabin;
    private final TravellerClassCabin travellerClassCabin;
private final Scanner keyboard = new Scanner(System.in);

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
        String userSeatSelection;

        try {

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

                System.out.print("specific seat|1");

                boolean isIndividualSeatAvailable = this.isSpecificSeatAvailable(passengerClass, userSeatSelection);

                System.out.println("is isIndividualSeatAvailable: + " + isIndividualSeatAvailable);

                if (userSeatSelection.matches("[0-9A-Z]+") && !isIndividualSeatAvailable) {

                    System.out.println("user wanted a specific seat, but not available: + ");

                    System.out.println("choose a specific seat again");

                    userSeatSelection = keyboard.next().toUpperCase();

                    System.out.println("user seat choice " + userSeatSelection);

                    seat = fetchSeat(passengerClass, userSeatSelection);

                    System.out.println("returned seat: " + seat);

                }
                System.out.println("user seat choice " + userSeatSelection);

                seat = fetchSeat(passengerClass, userSeatSelection);

                System.out.println("returned seat: " + seat);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter a valid class.");
        }
        return seat;
    }

    private Seat fetchSeatByClassByType(PassengerClass passengerClass, String seatType) {

        if(passengerClass.equals(PassengerClass.FIRST)) {
            for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.firstClassCabin.seats[row][seatIndex];

                    if (seat.isAvailable()&&seat.getType().toString().toUpperCase().equals(seatType.toUpperCase().trim())) {

                        System.out.println("found available seat matching seat type;");

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

                        return seat;
                    }

                }
            }
        }
        return null;
    }
    public void chooseSeatsForGroup(PassengerClass classSelection, int  groupSize) {

        String userSeatSelection;

        List<Passenger> plist = new ArrayList<>();

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

                System.out.print("  Enter the 3rd passenger’s last name: ");

                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("userSeatSelection: " + userSeatSelection);
                pass.setLastName(userSeatSelection);

                System.out.print("  Enter the 3rd passport number: ");

                userSeatSelection = keyboard.next().toUpperCase();

                System.out.println("userSeatSelection: " + userSeatSelection);
                pass.setPassportNumber(userSeatSelection);
                plist.add(pass);

            }
        }
        for(Passenger passenger:plist){
            reserveFirstAvailableSeatInClass(classSelection,passenger);}
    }

    private void reserveFirstAvailableSeatInClass(PassengerClass passengerClass, Passenger passenger ) {

        if(passengerClass.equals(PassengerClass.FIRST)) {
            for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.firstClassCabin.seats[row][seatIndex];

                    if (seat.isAvailable()) {

                        System.out.println("found available seat;");

                        seat.setBooked(true);
                        seat.setPassenger(passenger);

                      return;
                    }
                }
            }
        }

        if(passengerClass.equals(PassengerClass.BUSINESS)) {
            for (int row = 0; row < this.businessClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.businessClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.businessClassCabin.seats[row][seatIndex];

                    if (seat.isAvailable()) {

                        System.out.println("found available seat;");

                        seat.setBooked(true);
                        seat.setPassenger(passenger);

                        return;
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

                        return;
                    }
                }
            }
        }
    }



        public Seat fetchSeat(PassengerClass passengerClass, String seatSelection) {

        System.out.println(" enter ::  fetchSeat()");

        int rowNumber = Integer.parseInt(seatSelection.replaceAll("[A-Z]+", ""));//

        char seatLetter = seatSelection.replaceAll("[0-9]+", "").charAt(0);

        System.out.println("rowNumber: " + rowNumber);


        int logicalSeatRow=-1;


        System.out.println("raw this.firstClassCabin.logicalRowNumberOffset: " + this.firstClassCabin.logicalRowNumberOffset);
        System.out.println("raw this.businessClassCabin.logicalRowNumberOffset: " +this.businessClassCabin.logicalRowNumberOffset);

        System.out.println("raw this.travellerClassCabin.logicalRowNumberOffset: " +this.travellerClassCabin.logicalRowNumberOffset);

        switch (passengerClass) {
            case FIRST ->logicalSeatRow=rowNumber- this.firstClassCabin.logicalRowNumberOffset;
            case BUSINESS ->logicalSeatRow=rowNumber- this.businessClassCabin.logicalRowNumberOffset;
            case TRAVELLER -> logicalSeatRow=rowNumber- this.travellerClassCabin.logicalRowNumberOffset;
        }

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

                Seat seat = firstClassCabin.seats[logicalSeatRow][firstClassCabin.deriveSeatNumberForSeatLater(seatLetter)];

                System.out.println("seat from the fetch" + seat);

                return seat;

            case BUSINESS:

                System.out.println("BUSINESS BUSINESS BUSINESS");

                System.out.println("logicalSeatRow!!!: " + logicalSeatRow);

                seat = this.businessClassCabin.seats[logicalSeatRow][businessClassCabin.deriveSeatNumberForSeatLater(seatLetter)];

                System.out.println("seat from the fetch" + seat);

                return seat;

            case TRAVELLER:

                System.out.println("TRAVELLER TRAVELLER TRAVELLER");

                seat = this.travellerClassCabin.seats[logicalSeatRow][travellerClassCabin.deriveSeatNumberForSeatLater(seatLetter)];

                System.out.println("seat from the fetch" + seat);

                return seat;

            default:

                return null;
        }
    }

    public boolean isSpecificSeatAvailable(PassengerClass passengerClass, String seatSelection) {

        System.out.println(" enter ::  isSeatAvailable()");

        int rowNumber = Integer.parseInt(seatSelection.replaceAll("[A-Z]+", ""));//
        char seatLetter = seatSelection.replaceAll("[0-9]+", "").charAt(0);

        System.out.println("raw rowNumber: " + rowNumber);

        int adjustedrownum=-1;


        System.out.println("raw this.firstClassCabin.logicalRowNumberOffset: " + this.firstClassCabin.logicalRowNumberOffset);
        System.out.println("raw this.businessClassCabin.logicalRowNumberOffset;: " +this.businessClassCabin.logicalRowNumberOffset);

        System.out.println("raw this.travellerClassCabin.logicalRowNumberOffset: " +this.travellerClassCabin.logicalRowNumberOffset);

        switch (passengerClass) {
            case FIRST ->adjustedrownum=rowNumber- this.firstClassCabin.logicalRowNumberOffset;
            case BUSINESS ->adjustedrownum=rowNumber- this.businessClassCabin.logicalRowNumberOffset;
            case TRAVELLER -> adjustedrownum=rowNumber- this.travellerClassCabin.logicalRowNumberOffset;
        }

        System.out.println("adjustedRowNum: " + adjustedrownum);

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

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter a valid class.");
            return null;
        }
    }
public void cancelFlight(String passportNumber) {

    System.out.println("cancelFlight // enter ");

    System.out.println("deleting a reservation w/ passport number" + passportNumber);

    Seat foundSeat = null;

    for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
        for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

            Seat seat = this.firstClassCabin.seats[row][seatIndex];
            foundSeat = getSeat(passportNumber, foundSeat, seat);
        }
    }

    for (int row = 0; row < this.businessClassCabin.getNumberOfRows(); row++) {
        for (int seatIndex = 0; seatIndex < this.businessClassCabin.getSeatLetters().length; seatIndex++) {

            Seat seat = this.businessClassCabin.seats[row][seatIndex];
            System.out.println("checking: "+seat);

            if (seat.isBooked() && Objects.equals(seat.getPassenger().getPassportNumber(), passportNumber)) {

                System.out.println("eat.isBooked(): "+seat.isBooked());


                System.out.println("seat.getPassenger().getPassportNumber(): "+seat.getPassenger().getPassportNumber());
                System.out.println("passportNumber"+seat);

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
            foundSeat = getSeat(passportNumber, foundSeat, seat);

        }
    }
    System.out.println("found seat that was deleted:"+foundSeat);
}

    private Seat getSeat(String passportNumber, Seat foundSeat, Seat seat) {
        System.out.println("checking: "+seat);

        if (seat.isBooked() && Objects.equals(seat.getPassenger().getPassportNumber(), passportNumber)) {

            System.out.println("found seat matching passport;");

            seat.setBooked(false);
            seat.setPassenger(null);

            foundSeat = seat;
        }
        return foundSeat;
    }

    public void displayPassengerInfoByPassportNumber(String passportNumber) {

        System.out.println("displayPassengerInfoByPassportNumber // enter ");

        Seat foundSeat = null;

        for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.firstClassCabin.seats[row][seatIndex];

                if (seat.isBooked() && Objects.equals(seat.getPassenger().getPassportNumber(), passportNumber)) {

                    System.out.println("found seat matching passport;");

                    foundSeat = seat;
                }
            }
        }

        for (int row = 0; row < this.businessClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.businessClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.businessClassCabin.seats[row][seatIndex];

                if (seat.isBooked() && Objects.equals(seat.getPassenger().getPassportNumber(), passportNumber)) {
                    foundSeat = seat;
                }
            }
        }

        for (int row = 0; row < this.travellerClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.travellerClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.travellerClassCabin.seats[row][seatIndex];

                if (seat.isBooked() && Objects.equals(seat.getPassenger().getPassportNumber(), passportNumber)) {
                    foundSeat = seat;
                }
            }
        }

        if(foundSeat!=null) {

        System.out.println("First name\tLast name\tPassport number\tSeat reserved\tClass\tType");
//First name	Last name	Passport number	Seat reserved	Class	Type
//Akram	Khan	JJO3412	24A	Traveller	Window
            seatInfo(foundSeat);

            System.out.println(foundSeat.getType());
        System.out.print("\t");
        }
    }

    public void displayPassengerInfoByPassengerLastName(String lastName) {

            System.out.println("displayPassengerInfoByPassportNumber // enter ");

            Seat foundSeat = null;

            for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
                for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

                    Seat seat = this.firstClassCabin.seats[row][seatIndex];

                        if (seat.isBooked() && Objects.equals(seat.getPassenger().getLastName().toUpperCase().trim(),
                                lastName.toUpperCase().trim()
                        )) {

                            System.out.println("found seat matching last name;");
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

        if (foundSeat!=null) {
            System.out.println("First name\tLast name\tPassport number\tSeat reserved\tClass\tType");
//First name	Last name	Passport number	Seat reserved	Class	Type
//Akram	Khan	JJO3412	24A	Traveller	Window
            seatInfo(foundSeat);

            System.out.println(foundSeat.getType());
            System.out.print("\t");

        }
    }

    public void calculateRevenueFromReservations() {

        // declare counters by class
        int firstClassCounter = 0;
        int businessClassCounter = 0;
        int travellerClassCounter = 0;

        // iterate through first class, increment firstClassCounter by 1 for every booked seat found
        for (int row = 0; row < this.firstClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.firstClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.firstClassCabin.seats[row][seatIndex];

                if (seat.isBooked()) {

                    System.out.println("found booked seat");

                    firstClassCounter++;
                }
            }
        }
        // iterate through business class, increment businessClassCounter by 1 for every booked seat found
        for (int row = 0; row < this.businessClassCabin.getNumberOfRows(); row++) {
            for (int seatIndex = 0; seatIndex < this.businessClassCabin.getSeatLetters().length; seatIndex++) {

                Seat seat = this.businessClassCabin.seats[row][seatIndex];

                if (seat.isBooked()) {

                    System.out.println("found booked seat");

                    businessClassCounter++;
                }
            }
        }
        
        // iterate through traveller class, increment travellerClassCounter by 1 for every booked seat found
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

        // output the number of seats booked in the cabin, and the total revenue from that cabin
        System.out.println("First\t\t" +firstClassCounter +"\t $" + firstClassCounter * 1800);
        System.out.println("Business\t\t"+ businessClassCounter +"\t $" +  businessClassCounter * 1200);
        System.out.println("Traveller\t" + travellerClassCounter +"\t $" + travellerClassCounter * 1000);
        
        System.out.println();
    }
    public void generateReportOfUserReservation() {

        List<Seat> seatList = new ArrayList<>();

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


        Collections.sort(seatList);

      System.out.println("  First name	Last name	Passport number	Seat reserved	Class	Type");

      for(Seat seat:seatList) {

          seatInfo(seat);
          System.out.print(seat.getType());System.out.print("\t");

          System.out.println();
      }
    }

    private void seatInfo(Seat seat) {
        System.out.print(seat.getPassenger().getFirstName());
        System.out.print("\t");
        System.out.print(seat.getPassenger().getLastName());
        System.out.print("\t");
        System.out.print(seat.getPassenger().getPassportNumber());
        System.out.print("\t");
        System.out.print(seat.getSeatNumber());
        System.out.print("\t");
        System.out.print(seat.getPassengerClass());
        System.out.print("\t");
    }
}
