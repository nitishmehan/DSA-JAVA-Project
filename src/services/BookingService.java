package services;

import models.Booking;
import models.Passenger;
import models.Train;
import dsa.PassengerQueue;
import utils.InputHelper;

import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private List<Booking> bookings;
    private PassengerQueue waitlist;
    private TrainService trainService;
    private PassengerService passengerService;

    public BookingService(TrainService trainService, PassengerService passengerService) {
        this.bookings = new ArrayList<>();
        this.waitlist = new PassengerQueue();
        this.trainService = trainService;
        this.passengerService = passengerService;
    }

    public boolean bookTicket(Train train, Passenger passenger) {
        if (train.bookTicket()) {
            Booking booking = new Booking(train, passenger);
            bookings.add(booking);
            System.out.println("Booking successful! Booking ID: " + booking.getBookingId());
            return true;
        } else {
            waitlist.enqueue(passenger);
            System.out.println("No available seats. Passenger added to waitlist.");
            return false;
        }
    }

    public void cancelBookingById(String bookingId) {
        Booking bookingToRemove = null;
        for (Booking b : bookings) {
            if (b.getBookingId().equals(bookingId)) {
                bookingToRemove = b;
                break;
            }
        }
        if (bookingToRemove != null) {
            bookings.remove(bookingToRemove);
            Train train = bookingToRemove.getTrain();
            train.cancelTicket();
            if (!waitlist.isEmpty()) {
                Passenger nextPassenger = waitlist.dequeue();
                bookTicket(train, nextPassenger);
            }
            System.out.println("Booking cancelled.");
        } else {
            System.out.println("Booking not found.");
        }
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public void displayWaitlist() {
        waitlist.display();
    }

    public void manageBookings() {
        int choice;
        do {
            System.out.println("\n--- Booking Management ---");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Booking");
            System.out.println("3. View All Bookings");
            System.out.println("4. View Waitlist");
            System.out.println("0. Back to Main Menu");
            choice = InputHelper.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    bookTicketMenu();
                    break;
                case 2:
                    cancelBookingMenu();
                    break;
                case 3:
                    viewAllBookingsMenu();
                    break;
                case 4:
                    displayWaitlist();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void bookTicketMenu() {
        int trainNumber = InputHelper.getIntInput("Enter train number: ");
        Train train = trainService.viewTrain(trainNumber);
        if (train == null) {
            System.out.println("Train not found.");
            return;
        }
        String passengerId = InputHelper.getStringInput("Enter passenger ID: ");
        Passenger passenger = passengerService.getPassengerById(passengerId);
        if (passenger == null) {
            System.out.println("Passenger not found.");
            return;
        }
        bookTicket(train, passenger);
    }

    private void cancelBookingMenu() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings to cancel.");
            return;
        }
        System.out.println("--- All Bookings ---");
        for (Booking b : bookings) {
            System.out.println("Booking ID: " + b.getBookingId() + ", Train: " + b.getTrain().getTrainNumber() + ", Passenger: " + b.getPassenger().getPassengerId());
        }
        String bookingId = InputHelper.getStringInput("Enter Booking ID to cancel: ");
        cancelBookingById(bookingId);
    }

    private void viewAllBookingsMenu() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        System.out.println("--- All Bookings ---");
        for (Booking b : bookings) {
            System.out.println("Booking ID: " + b.getBookingId() + ", Train: " + b.getTrain().getTrainNumber() + ", Passenger: " + b.getPassenger().getPassengerId());
        }
    }
}