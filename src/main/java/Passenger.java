import java.util.ArrayList;

public class Passenger {

    private String name;
    private ArrayList<Bag> bags;
    private ArrayList<BoardingCard> boardingCards;

    public Passenger(String name) {
        this.name = name;
        this.bags = new ArrayList<Bag>();
        this.boardingCards = new ArrayList<BoardingCard>();
    }

    public void addBag(Bag bag){
        this.bags.add(bag);
    }

    public void addBoardingCard(BoardingCard boardingCard){
        this.boardingCards.add(boardingCard);
    }

    public ArrayList<BoardingCard> getBoardingCards() {
        return boardingCards;
    }

    public ArrayList<Bag> getBags() {
        return bags;
    }

    public String getName() {
        return name;
    }
}
