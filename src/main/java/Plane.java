public class Plane {
    private PlaneType planeType;

    public Plane(PlaneType planeType) {
        this.planeType = planeType;
    }

    public PlaneType getPlaneType() {
        return planeType;
    }

    public int getReservedBagsWeight() {
        return this.planeType.getReservedBagsWeight();
    }
}
