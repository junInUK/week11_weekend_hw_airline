public class BoardingCard {
    private Passenger passenger;
    private Flight flight;
    private int gate;
    private int seatNo;

    public BoardingCard(int seatNo) {
        this.passenger = null;
        this.flight = null;
        this.gate = 0;
        this.seatNo = seatNo;
    }

    public BoardingCard(Passenger passenger, Flight flight, int gate, int seatNo) {
        this.passenger = passenger;
        this.flight = flight;
        this.gate = gate;
        this.seatNo = seatNo;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getGate() {
        return gate;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }
}
