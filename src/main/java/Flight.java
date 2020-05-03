import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Flight {

    private ArrayList<Passenger> bookedPassengers;
    private ArrayList<BoardingCard> boardingCards;
    private ArrayList<BoardingCard> bookedBoardingCards;
    private Plane plane;
    private String flightNumber;
    private Airport departure;
    private Airport destination;
    private Date departureTime;

    public Flight(Plane plane, String flightNumber, Airport departure, Airport destination, Date departureTime) {
        this.plane = plane;
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.bookedPassengers = new ArrayList<Passenger>();
        this.boardingCards = new ArrayList<BoardingCard>();
        for(int i = 0; i < this.plane.getPlaneType().getCapacity(); i++){
            BoardingCard boardingCard = new BoardingCard(i);
            this.boardingCards.add(boardingCard);
        }
        this.bookedBoardingCards = new ArrayList<BoardingCard>();
    }

    public BoardingCard generateRandomBoardingCard(){
        Random random = new Random();
        int seatNo = random.nextInt(this.boardingCards.size());
 //       System.out.println("seatNo:" + seatNo);
        return boardingCards.remove(seatNo);
    }

    public void addBookedBoardingCard(BoardingCard boardingCard){
        this.bookedBoardingCards.add(boardingCard);
    }

    public int getAvailableSeats(){
        int availableSeats = this.plane.getPlaneType().getCapacity() - this.bookedPassengers.size();
        return availableSeats;
    }

    public int getReservedBagsWeight(){
        return this.plane.getReservedBagsWeight();
    }

    public ArrayList<Passenger> getBookedPassengers(){
        return this.bookedPassengers;
    }

    public Plane getPlane() {
        return plane;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Airport getDestination() {
        return destination;
    }

    public Passenger getBookedPassengerByName(String name) {
        for(Passenger passenger : this.bookedPassengers){
            if( passenger.getName() == name){
                return passenger;
            }
        }
        return null;
    }

    public int calculateBookedBaggageWeight() {
        int totalBookedBaggageWeight = 0;
        for(Passenger passenger : this.bookedPassengers){
            for(Bag bag : passenger.getBags()){
                totalBookedBaggageWeight += bag.getWeight();
            }
        }
        return totalBookedBaggageWeight;
    }

    public int calculateBaggageWeightRemain() {
        return (int)this.plane.getPlaneType().getWeight()/2 - this.calculateBookedBaggageWeight();
    }

    public ArrayList<BoardingCard> getBoardingCards() {
        return boardingCards;
    }

    public ArrayList<BoardingCard> getBookedBoardingCards() {
        return bookedBoardingCards;
    }

    public void sortPassengersBySeatNumber(){
        for(int i = 0;i < this.bookedBoardingCards.size(); i++){
            for(int j=1;j<this.bookedBoardingCards.size()-i;j++){
                if(this.bookedBoardingCards.get(j-1).getSeatNo() > this.bookedBoardingCards.get(j).getSeatNo()){
                    BoardingCard temp = this.bookedBoardingCards.get(j);
                    this.bookedBoardingCards.set(j,this.bookedBoardingCards.get(j-1));
                    this.bookedBoardingCards.set(j-1,temp);
                }
            }
        }
    }

    public Passenger getBookedPassengerBySeatNo(int seatNo){
        return this.binarySearch(this.bookedBoardingCards,seatNo);
    }

    public Passenger binarySearch(ArrayList<BoardingCard> searchBoardingCards, int seatNo) {
//        this.sortPassengersBySeatNumber();
        int size = searchBoardingCards.size();
        if(0 == size){
            return null;
        }

        if(seatNo < searchBoardingCards.get(0).getSeatNo() || seatNo > searchBoardingCards.get(size-1).getSeatNo()){
            return null;
        }

        int midIndex = 0;
        if(size > 1){
            midIndex = (int) Math.ceil((double) size/2);
        }
        BoardingCard midPoint = searchBoardingCards.get(midIndex);
        if(seatNo == midPoint.getSeatNo()){
            return midPoint.getPassenger();
        }
        ArrayList<BoardingCard> newSearchArea;
        if(seatNo < midPoint.getSeatNo()){
            newSearchArea = new ArrayList<BoardingCard>(searchBoardingCards.subList(0,midIndex));
        }else{
            newSearchArea = new ArrayList<BoardingCard>(searchBoardingCards.subList(midIndex + 1, searchBoardingCards.size()));
        }
        return binarySearch(newSearchArea,seatNo);
    }


//    public Boolean binarySearch(ArrayList<Integer> array, int searchNumber){
//        if (array.size() == 0){
//            return false;
//        }
//
//        int midIndex = 0;
//        if (array.size() >1) {
//            midIndex = (int) Math.ceil((double) array.size() / 2);
//        }
//
//        int midPoint = array.get(midIndex);
//
//        if (searchNumber == midPoint){
//            return true;
//        }
//
//        ArrayList<Integer> newSearchArea;
//
//        if (searchNumber < midPoint){
//            newSearchArea = new ArrayList<Integer>(array.subList(0, midIndex));
//        } else {
//            newSearchArea = new ArrayList<Integer>(array.subList(midIndex + 1, array.size()));
//        }
//        return binarySearch(newSearchArea, searchNumber);
//    }


}
