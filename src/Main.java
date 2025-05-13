import services.TrainService;
import services.PassengerService;
import services.BookingService;
import services.UserService;
import models.User;
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
    private UserService userService;
    private User currentUser;

    public RailwayBookingSystem() {
        trainService = new TrainService();
        passengerService = new PassengerService();
        bookingService = new BookingService(trainService, passengerService);
        userService = new UserService();
        addDummyUsers();
        addDummyTrains();
        addDummyPassengers();
    }

    private void addDummyUsers() {
        // admin is already added by UserService constructor
        userService.signup("P001", "alice123", "user");
        userService.signup("P002", "bob123", "user");
        userService.signup("P003", "charlie123", "user");
        userService.signup("P004", "diana123", "user");
        userService.signup("P005", "eve123", "user");
    }

    private void addDummyTrains() {
        trainService.addTrain(new models.Train(101, "Shatabdi Express", "Delhi-Punjab", "08:00", 2, "08:00"));
        trainService.addTrain(new models.Train(102, "Shatabdi Express", "Punjab-Delhi", "08:00", 2, "08:00"));
        trainService.addTrain(new models.Train(103, "Rajdhani Express", "Delhi-Kolkata", "09:30", 10, "09:30"));
        trainService.addTrain(new models.Train(104, "Rajdhani Express", "Kolkata-Delhi", "09:30", 10, "09:30"));
        trainService.addTrain(new models.Train(105, "InterCity Express", "Mumbai-Chennai", "10:15", 4, "10:15"));
        trainService.addTrain(new models.Train(106, "InterCity Express", "Chennai-Mumbai", "10:15", 4, "10:15"));
        trainService.addTrain(new models.Train(107, "Jan Shatabdi", "Kolkata-Patna", "12:45", 11, "12:45"));
        trainService.addTrain(new models.Train(108, "Jan Shatabdi", "Patna-Kolkata", "12:45", 11, "12:45"));
    }

    private void addDummyPassengers() {
        passengerService.registerPassenger(new models.Passenger("P001", "Alice", 28, "alice@example.com"));
        passengerService.registerPassenger(new models.Passenger("P002", "Bob", 35, "bob@example.com"));
        passengerService.registerPassenger(new models.Passenger("P003", "Charlie", 22, "charlie@example.com"));
        passengerService.registerPassenger(new models.Passenger("P004", "Diana", 30, "diana@example.com"));
        passengerService.registerPassenger(new models.Passenger("P005", "Eve", 40, "eve@example.com"));
    }

    public void start() {
        loginOrSignup();
        int choice;
        do {
            displayMenu();
            choice = InputHelper.getIntInput("Enter your choice: ");
            System.out.println();
            if (choice == -1) break; // Defensive: allow breaking on error
            if (handleUserChoice(choice)) {
                // If logout, restart login/signup
                loginOrSignup();
            }
        } while (choice != 0);
    }

    private void loginOrSignup() {
        while (true) {
            System.out.println();
            System.out.println("Welcome to Railway Booking Management System");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            int option = InputHelper.getIntInput("Enter your choice: ");
            System.out.println();
            if (option == 1) {
                String username = InputHelper.getStringInput("Username: ");
                String password = InputHelper.getStringInput("Password: ");
                System.out.println();
                User user = userService.login(username, password);
                if (user != null) {
                    currentUser = user;
                    System.out.println("Login successful! Logged in as " + user.getRole());
                    break;
                } else {
                    System.out.println("Invalid credentials. Try again.");
                }
            } else if (option == 2) {
                String username = InputHelper.getStringInput("Choose username: ");
                String password = InputHelper.getStringInput("Choose password: ");
                System.out.println();
                // Always assign "user" role, do not ask for role
                if (username.equalsIgnoreCase("admin")) {
                    System.out.println("Cannot signup as admin.");
                    continue;
                }
                boolean success = userService.signup(username, password, "user");
                if (success) {
                    // Prompt for passenger details and register passenger profile
                    String name = InputHelper.getStringInput("Enter your name: ");
                    int age = InputHelper.getIntInput("Enter your age: ");
                    String contact = InputHelper.getStringInput("Enter your contact info: ");
                    passengerService.registerPassenger(new models.Passenger(username, name, age, contact));
                    System.out.println("\nSignup successful! Please login.");
                } else {
                    System.out.println("Username already exists.");
                }
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("Railway Booking Management System");
        if (currentUser.getRole().equals("admin")) {
            System.out.println("1. Manage Trains");
            System.out.println("2. Manage Passengers");
            System.out.println("3. Manage Bookings");
            System.out.println("4. Logout");
            System.out.println("0. Exit");
        } else {
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Booking");
            System.out.println("3. View My Bookings");
            System.out.println("4. View Waitlist");
            System.out.println("5. View All Trains");
            System.out.println("6. Find Train By Name");
            System.out.println("7. Display Trains Sorted By");
            System.out.println("8. Logout");
            System.out.println("0. Exit");
        }
    }

    // Returns true if user logged out, false otherwise
    private boolean handleUserChoice(int choice) {
        if (currentUser.getRole().equals("admin")) {
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
                case 4:
                    logout();
                    return true;
                case 0:
                    System.out.println("\nExiting the system. Thank you!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } else {
            switch (choice) {
                case 1:
                    bookingService.bookTicketMenu(currentUser.getUsername());
                    break;
                case 2:
                    bookingService.cancelBookingMenu(currentUser.getUsername());
                    break;
                case 3:
                    bookingService.viewUserBookingsMenu(currentUser.getUsername());
                    break;
                case 4:
                    bookingService.displayWaitlist();
                    break;
                case 5:
                    trainService.viewAllTrainsMenu();
                    break;
                case 6:
                    trainService.findTrainByNameMenu();
                    break;
                case 7:
                    trainService.displayTrainsSortedMenu();
                    break;
                case 8:
                    logout();
                    return true;
                case 0:
                    System.out.println("\nExiting the system. Thank you!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
        return false;
    }

    private void logout() {
        System.out.println("\nLogged out successfully.");
        currentUser = null;
    }
}