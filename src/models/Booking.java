package models;

public class Booking {
    private static int bookingCounter = 1; // Global counter for booking IDs
    private String bookingId;
    private Train train;
    private Passenger passenger;

    public Booking(Train train, Passenger passenger) {
        this.bookingId = String.valueOf(bookingCounter++);
        this.train = train;
        this.passenger = passenger;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Train getTrain() {
        return train;
    }

    public Passenger getPassenger() {
        return passenger;
    }
}