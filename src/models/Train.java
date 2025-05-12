package models;

public class Train {
    private int trainNumber;
    private String name;
    private String route;
    private String timing;
    private int seatCapacity;
    private int availableSeats;
    private String departureTime; // Added field

    public Train(int trainNumber, String name, String route, String timing, int seatCapacity, String departureTime) {
        this.trainNumber = trainNumber;
        this.name = name;
        this.route = route;
        this.timing = timing;
        this.seatCapacity = seatCapacity;
        this.availableSeats = seatCapacity;
        this.departureTime = departureTime;
    }

    // For backward compatibility, keep the old constructor
    public Train(int trainNumber, String name, String route, String timing, int seatCapacity) {
        this(trainNumber, name, route, timing, seatCapacity, timing); // Use timing as departureTime if not provided
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }

    public String getTiming() {
        return timing;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void updateTrain(String name, String route, String timing, int seatCapacity, String departureTime) {
        this.name = name;
        this.route = route;
        this.timing = timing;
        this.seatCapacity = seatCapacity;
        this.availableSeats = seatCapacity;
        this.departureTime = departureTime;
    }

    public boolean bookTicket() {
        if (availableSeats > 0) {
            availableSeats--;
            return true; // Booking successful
        }
        return false; // No available seats
    }

    public void cancelTicket() {
        if (availableSeats < seatCapacity) {
            availableSeats++;
        }
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainNumber=" + trainNumber +
                ", name='" + name + '\'' +
                ", route='" + route + '\'' +
                ", timing='" + timing + '\'' +
                ", seatCapacity=" + seatCapacity +
                ", availableSeats=" + availableSeats +
                ", departureTime='" + departureTime + '\'' +
                '}';
    }
}