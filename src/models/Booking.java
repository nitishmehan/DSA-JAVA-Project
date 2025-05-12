package models;

import java.util.UUID;

public class Booking {
    private String bookingId;
    private Train train;
    private Passenger passenger;

    public Booking(Train train, Passenger passenger) {
        this.bookingId = UUID.randomUUID().toString();
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