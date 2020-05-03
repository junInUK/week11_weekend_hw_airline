import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class FlightTest {

    private Flight flight;
    private Flight flight1;
    private Plane boeing747;
    private Plane learjet7075;
    private Passenger sam;
    private Passenger jun;
    private Passenger lily;
    private Passenger adam;
    private Passenger john;
    private Passenger jane;
    private Passenger william;
    private Bag bag1;
    private Bag bag2;
    private Bag bag3;

    @Before
    public void before(){
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date departureDate1 = null, departureDate2 = null;
        try {
            departureDate1 = dateFormat.parse("2020-5-19 09:15:00");
            departureDate2 = dateFormat.parse("2020-5-19 12:10:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        flight = new Flight(boeing747,"KL1007",Airport.AMS,Airport.LHR,departureDate1);
        flight1 = new Flight(learjet7075,"BA0309",Airport.CDG,Airport.LHR,departureDate2);
    }

    @Test
    public void bookedPassengersStartFromEmpty(){
        assertEquals(0,flight.getBookedPassengers().size());
    }

    @Test
    public void hasAPlane(){
        assertEquals(PlaneType.BOEING747,flight.getPlane().getPlaneType());
    }

    @Test
    public void hasAFlightNumber(){
        assertEquals("KL1007",flight.getFlightNumber());
    }

    @Test
    public void hasDestination(){
        assertEquals(Airport.LHR,flight.getDestination());
    }

    @Test
    public void canGetAvailableSeats(){
        assertEquals(568,flight.getAvailableSeats());
    }

    @Test
    public void canGenerateABoardingCard(){
        int seatNo = flight.generateRandomBoardingCard().getSeatNo();
        System.out.println("In FlightTest seatNo:" + seatNo);
        assertTrue( seatNo >=0 && seatNo < flight.getPlane().getPlaneType().getCapacity());
        assertEquals(567,flight.getBoardingCards().size());
    }

    @Test
    public void canGetBoardingCards(){
        for(BoardingCard boardingCard : flight1.getBoardingCards()){
            System.out.println(boardingCard.getSeatNo());
        }
    }

    @Test
    public void canSortPassengerBySeatNumber(){

    }
}
