public enum PlaneType {
    BOEING747(568,112760),
    DOUGLASDC8(259,54300),
    LEARJET7075(6,1320);

    private final int capacity, weight;

    PlaneType(int capacity, int weight) {
        this.capacity = capacity;
        this.weight = weight;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getWeight() {
        return weight;
    }

    public int getReservedBagsWeight() {
        return (int)weight/2/capacity;
    }
}
