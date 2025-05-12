import services.TrainService;
import services.PassengerService;
import services.BookingService;
import utils.InputHelper;

public class Main {
    public static void main(String[] args) {
        RailwayBookingSystem system = new RailwayBookingSystem();
        system.start();
    }
}

class RailwayBookingSystem {
    private TrainService trainService;
    private PassengerService passengerService;
    private BookingService bookingService;

    public RailwayBookingSystem() {
        trainService = new TrainService();
        passengerService = new PassengerService();
        bookingService = new BookingService(trainService, passengerService);
    }

    public void start() {
        int choice;
        do {
            displayMenu();
            choice = InputHelper.getIntInput("Enter your choice: ");
            handleUserChoice(choice);
        } while (choice != 0);
    }

    private void displayMenu() {
        System.out.println("Railway Booking Management System");
        System.out.println("1. Manage Trains");
        System.out.println("2. Manage Passengers");
        System.out.println("3. Manage Bookings");
        System.out.println("0. Exit");
    }

    private void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                trainService.manageTrains();
                break;
            case 2:
                passengerService.managePassengers();
                break;
            case 3:
                bookingService.manageBookings();
                break;
            case 0:
                System.out.println("Exiting the system. Thank you!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}