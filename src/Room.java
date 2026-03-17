public abstract class Room {

    protected String type;
    protected int beds;
    protected double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println(type + " | Beds: " + beds + " | Price: " + price);
    }
}