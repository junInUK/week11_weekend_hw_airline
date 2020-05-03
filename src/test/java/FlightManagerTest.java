import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class FlightManagerTest {

    private FlightManager flightManager;
    private Flight flight1;
    private Flight flight2;
    private Plane learjet7075;
    private Plane boeing747;
    private Bag bag1;
    private Bag bag2;
    private Bag bag3;
    private Passenger sam;
    private Passenger jun;
    private Passenger lily;
    private Passenger adam;
    private Passenger john;
    private Passenger jane;
    private Passenger william;

    @Before
    public void before() throws ParseException {
        bag1 = new Bag(50);
        bag2 = new Bag(40);
        bag3 = new Bag(20);
        sam = new Passenger("sam");
        jun = new Passenger("jun");
        lily = new Passenger("lily");
        adam = new Passenger("adam");
        john = new Passenger("john");
        jane = new Passenger("jane");
        william = new Passenger("william");
        boeing747 = new Plane(PlaneType.BOEING747);
        learjet7075 = new Plane(PlaneType.LEARJET7075);

        flightManager = new FlightManager();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date departureDate1 = null, departureDate2 = null;
        try {
            departureDate1 = dateFormat.parse("2020-5-19 09:15:00");
            departureDate2 = dateFormat.parse("2020-5-19 12:10:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        flight1 = new Flight(boeing747,"KL1007",Airport.AMS,Airport.LHR,departureDate1);
        flight2 = new Flight(learjet7075,"BA0309",Airport.CDG,Airport.LHR,departureDate2);
    }

    @Test
    public void canCalculateReservedBaggageWeight(){
        int reservedBaggageWeight = flightManager.calculaterReservedBaggageWeight(flight1);
        assertEquals(99, reservedBaggageWeight);
    }

    @Test
    public void canCalculateBookedBaggageWeight(){
        sam.addBag(bag1);
        boolean result = flightManager.bookAPassenger(sam, flight1);
        if(result){
            int bookedBaggageWeight = flightManager.calculateBookedBaggageWeight(flight1);
            assertEquals(50,bookedBaggageWeight);
        }
    }

    @Test
    public void canCalculateBaggageWeightRemain(){
        sam.addBag(bag1);
        flightManager.bookAPassenger(sam,flight1);
        assertEquals(56330,flight1.calculateBaggageWeightRemain());
    }

    @Test
    public void canBookAPassengerNoOverWeightBagsAndSeatAvailable(){
        sam.addBag(bag1);
        sam.addBag(bag2);
        assertEquals(true, flightManager.bookAPassenger(sam, flight1));
        assertEquals(1, flight1.getBookedPassengers().size());
        Passenger passenger = flight1.getBookedPassengerByName(sam.getName());
        if(passenger != null){
            assertEquals(2,passenger.getBags().size());
            assertEquals(1,passenger.getBoardingCards().size());
            for(BoardingCard ticket : passenger.getBoardingCards()){
                System.out.println("passenage name:" + ticket.getPassenger().getName());
                System.out.println("flight number:"  + ticket.getFlight().getFlightNumber());
                System.out.println("seatNo:" + ticket.getSeatNo());
            }
        }
    }

    @Test
    public void canNotBookAPassengerOverWeight(){
        sam.addBag(bag1);
        sam.addBag(bag2);
        sam.addBag(bag3);
        assertFalse(flightManager.bookAPassenger(sam,flight1));
        assertEquals(0,flight1.getBookedPassengers().size());
    }

    @Test
    public void canNotBookAPassangerWithOutAvailableSeat(){
        sam.addBag(bag1);

        assertTrue(flightManager.bookAPassenger(sam,flight2));
        assertTrue(flightManager.bookAPassenger(jun,flight2));
        assertTrue(flightManager.bookAPassenger(lily,flight2));
        assertTrue(flightManager.bookAPassenger(adam,flight2));
        assertTrue(flightManager.bookAPassenger(john,flight2));
        assertTrue(flightManager.bookAPassenger(jane,flight2));
        assertFalse(flightManager.bookAPassenger(william,flight2));
        for(Passenger passenger : flight2.getBookedPassengers()){
            for(BoardingCard ticket : passenger.getBoardingCards()){
                System.out.println("passenage name:" + ticket.getPassenger().getName() + " seatNo:" + ticket.getSeatNo());
            }
        }
        System.out.println("***************");
        flightManager.sortPassengersBySeatNo(flight2);
        for(BoardingCard boardingCard : flight2.getBookedBoardingCards()){
            System.out.println("Passenger name:" + boardingCard.getPassenger().getName() +" SeatNo:" + boardingCard.getSeatNo());
        }

        String name = flight2.getBookedPassengerBySeatNo(2).getName();
        System.out.println(name);
        assertEquals(null, flight2.getBookedPassengerByName(william.getName()));

    }
}
