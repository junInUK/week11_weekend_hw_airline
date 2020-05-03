import java.util.Random;

public class FlightManager {

    public FlightManager() {
    }

    public int calculaterReservedBaggageWeight(Flight flight) {
        return flight.getReservedBagsWeight();
    }

    public int calculateBookedBaggageWeight(Flight flight) {
        return flight.calculateBookedBaggageWeight();
    }

    public boolean bookAPassenger(Passenger passenger,Flight flight){
        int totalWeightOfBags = 0;
        for(Bag bag : passenger.getBags()){
            if(totalWeightOfBags + bag.getWeight() <= flight.getPlane().getReservedBagsWeight()){
                totalWeightOfBags += bag.getWeight();
            }else{
                System.out.println("Luggage cannot over " + flight.getPlane().getReservedBagsWeight() + " kg!");
                return false;
            }
        }
        if(flight.getAvailableSeats() > 0){
            flight.getBookedPassengers().add(passenger);
        }else{
            System.out.println("Sorry, no available seat!");
            return false;
        }
        BoardingCard ticket = flight.generateRandomBoardingCard();
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticket.setGate(48);
        passenger.addBoardingCard(ticket);
        flight.addBookedBoardingCard(ticket);
        return true;
    }

    public void sortPassengersBySeatNo(Flight flight) {
        flight.sortPassengersBySeatNumber();
    }
}
