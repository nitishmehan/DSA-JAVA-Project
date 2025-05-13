package services;

import models.Booking;
import models.Passenger;
import models.Train;
import dsa.PassengerQueue;
import dsa.PassengerQueue.PassengerTrainPair;
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
            System.out.println("\nBooking successful! Booking ID: " + booking.getBookingId());
            return true;
        } else {
            waitlist.enqueue(passenger, train.getTrainNumber()); // Pass train number to queue
            System.out.println("\nNo available seats. Passenger added to waitlist for train number " + train.getTrainNumber() + ".");
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
                PassengerTrainPair next = waitlist.dequeue();
                if (next != null && next.trainNumber == train.getTrainNumber()) {
                    bookTicket(train, next.passenger);
                } else if (next != null) {
                    // If not matching train, re-enqueue for their train
                    waitlist.enqueue(next.passenger, next.trainNumber);
                }
            }
            System.out.println("\nBooking cancelled.");
        } else {
            System.out.println("\nBooking not found.");
        }
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public void displayWaitlist() {
        System.out.println();
        if (waitlist.isEmpty()) {
            System.out.println("Waitlist is empty.");
            return;
        }
        System.out.println("--- Waitlist ---");
        printWaitlistTableHeader();
        waitlist.forEach((passenger, trainNumber) -> printWaitlistRow(passenger, trainNumber));
        printWaitlistTableFooter();
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
            System.out.println();
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
                    System.out.println("\nInvalid choice.");
            }
        } while (choice != 0);
    }

    private void bookTicketMenu() {
        System.out.println();
        int trainNumber = InputHelper.getIntInput("Enter train number: ");
        Train train = trainService.viewTrain(trainNumber);
        if (train == null) {
            System.out.println("\nTrain not found.");
            return;
        }
        String passengerId = InputHelper.getStringInput("Enter passenger ID: ");
        Passenger passenger = passengerService.getPassengerById(passengerId);
        if (passenger == null) {
            System.out.println("\nPassenger not found.");
            return;
        }
        bookTicket(train, passenger);
    }

    private void cancelBookingMenu() {
        System.out.println();
        if (bookings.isEmpty()) {
            System.out.println("No bookings to cancel.");
            return;
        }
        System.out.println("--- All Bookings ---");
        printBookingTableHeader();
        for (Booking b : bookings) {
            printBookingRow(b);
        }
        printBookingTableFooter();
        String bookingId = InputHelper.getStringInput("Enter Booking ID to cancel: ");
        cancelBookingById(bookingId);
    }

    private void viewAllBookingsMenu() {
        System.out.println();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        System.out.println("--- All Bookings ---");
        printBookingTableHeader();
        for (Booking b : bookings) {
            printBookingRow(b);
        }
        printBookingTableFooter();
    }

    public void bookTicketMenu(String username) {
        System.out.println();
        int trainNumber = utils.InputHelper.getIntInput("Enter train number: ");
        models.Train train = trainService.viewTrain(trainNumber);
        if (train == null) {
            System.out.println("\nTrain not found.");
            return;
        }
        models.Passenger passenger = passengerService.getPassengerById(username);
        if (passenger == null) {
            System.out.println("\nPassenger profile not found. Please ask admin to register you as a passenger.");
            return;
        }
        bookTicket(train, passenger);
    }

    public void cancelBookingMenu(String username) {
        System.out.println();
        boolean found = false;
        for (Booking b : bookings) {
            if (b.getPassenger().getPassengerId().equals(username)) {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No bookings to cancel.");
            return;
        }
        System.out.println("--- Your Bookings ---");
        printBookingTableHeader();
        for (Booking b : bookings) {
            if (b.getPassenger().getPassengerId().equals(username)) {
                printBookingRow(b);
            }
        }
        printBookingTableFooter();
        String bookingId = utils.InputHelper.getStringInput("Enter Booking ID to cancel: ");
        cancelBookingById(bookingId);
    }

    public void viewUserBookingsMenu(String username) {
        System.out.println();
        boolean found = false;
        for (Booking b : bookings) {
            if (b.getPassenger().getPassengerId().equals(username)) {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No bookings found.");
            return;
        }
        System.out.println("--- Your Bookings ---");
        printBookingTableHeader();
        for (Booking b : bookings) {
            if (b.getPassenger().getPassengerId().equals(username)) {
                printBookingRow(b);
            }
        }
        printBookingTableFooter();
    }

    private void printBookingTableHeader() {
        System.out.println("+-----------+------------+------------+---------------------+");
        System.out.printf("| %-9s | %-10s | %-10s | %-19s |\n", "BookingID", "Train No.", "Passenger", "Train Name");
        System.out.println("+-----------+------------+------------+---------------------+");
    }

    private void printBookingRow(models.Booking b) {
        System.out.printf("| %-9s | %-10d | %-10s | %-19s |\n",
            b.getBookingId(),
            b.getTrain().getTrainNumber(),
            b.getPassenger().getPassengerId(),
            b.getTrain().getName());
    }

    private void printBookingTableFooter() {
        System.out.println("+-----------+------------+------------+---------------------+");
    }

    private void printWaitlistTableHeader() {
        System.out.println("+------------+---------------------+-----+--------------------------+------------+");
        System.out.printf("| %-10s | %-19s | %-3s | %-24s | %-10s |\n", "PassengerID", "Name", "Age", "Contact Info", "Train No.");
        System.out.println("+------------+---------------------+-----+--------------------------+------------+");
    }

    private void printWaitlistRow(models.Passenger passenger, int trainNumber) {
        System.out.printf("| %-10s | %-19s | %-3d | %-24s | %-10d |\n",
            passenger.getPassengerId(),
            passenger.getName(),
            passenger.getAge(),
            passenger.getContactInfo(),
            trainNumber);
    }

    private void printWaitlistTableFooter() {
        System.out.println("+------------+---------------------+-----+--------------------------+------------+");
    }
}