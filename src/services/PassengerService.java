package services;

import models.Passenger;
import dsa.PassengerLinkedList;
import utils.InputHelper;

public class PassengerService {
    private PassengerLinkedList passengerList;

    public PassengerService() {
        passengerList = new PassengerLinkedList();
    }

    public void registerPassenger(Passenger passenger) {
        passengerList.add(passenger);
    }

    public void viewPassengers() {
        System.out.println();
        if (passengerList.isEmpty()) {
            System.out.println("No passengers registered.");
            return;
        }
        printPassengerTableHeader();
        passengerList.forEach(this::printPassengerRow);
        printPassengerTableFooter();
    }

    private void printPassengerTableHeader() {
        System.out.println("+------------+---------------------+-----+--------------------------+");
        System.out.printf("| %-10s | %-19s | %-3s | %-24s |\n", "PassengerID", "Name", "Age", "Contact Info");
        System.out.println("+------------+---------------------+-----+--------------------------+");
    }

    private void printPassengerRow(models.Passenger passenger) {
        System.out.printf("| %-10s | %-19s | %-3d | %-24s |\n",
            passenger.getPassengerId(),
            passenger.getName(),
            passenger.getAge(),
            passenger.getContactInfo());
    }

    private void printPassengerTableFooter() {
        System.out.println("+------------+---------------------+-----+--------------------------+");
    }

    public void editPassenger(String passengerId, Passenger updatedPassenger) {
        Passenger passenger = findPassengerById(passengerId);
        if (passenger != null) {
            passenger.setName(updatedPassenger.getName());
            passenger.setAge(updatedPassenger.getAge());
            passenger.setContactInfo(updatedPassenger.getContactInfo());
            System.out.println("\nPassenger updated successfully.");
        } else {
            System.out.println("\nPassenger not found.");
        }
    }

    public void removePassenger(String passengerId) {
        Passenger passenger = findPassengerById(passengerId);
        if (passenger != null) {
            passengerList.remove(passenger);
            System.out.println("\nPassenger removed successfully.");
        } else {
            System.out.println("\nPassenger not found.");
        }
    }

    private Passenger findPassengerById(String passengerId) {
        for (int i = 0; i < passengerList.size(); i++) {
            Passenger passenger = passengerList.get(i);
            if (passenger.getPassengerId().equals(passengerId)) {
                return passenger;
            }
        }
        return null;
    }

    public Passenger getPassengerById(String passengerId) {
        return findPassengerById(passengerId);
    }

    public void managePassengers() {
        int choice;
        do {
            System.out.println("\n--- Passenger Management ---");
            System.out.println("1. Register Passenger");
            System.out.println("2. View All Passengers");
            System.out.println("3. Edit Passenger");
            System.out.println("4. Remove Passenger");
            System.out.println("0. Back to Main Menu");
            choice = InputHelper.getIntInput("Enter your choice: ");
            System.out.println();
            switch (choice) {
                case 1:
                    registerPassengerMenu();
                    break;
                case 2:
                    viewPassengers();
                    break;
                case 3:
                    editPassengerMenu();
                    break;
                case 4:
                    removePassengerMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nInvalid choice.");
            }
        } while (choice != 0);
    }

    private void registerPassengerMenu() {
        System.out.println();
        String passengerId = InputHelper.getStringInput("Enter passenger ID: ");
        String name = InputHelper.getStringInput("Enter name: ");
        int age = InputHelper.getIntInput("Enter age: ");
        String contactInfo = InputHelper.getStringInput("Enter contact info: ");
        Passenger passenger = new Passenger(passengerId, name, age, contactInfo);
        registerPassenger(passenger);
        System.out.println("\nPassenger registered successfully.");
    }

    private void editPassengerMenu() {
        System.out.println();
        String passengerId = InputHelper.getStringInput("Enter passenger ID to edit: ");
        Passenger passenger = findPassengerById(passengerId);
        if (passenger == null) {
            System.out.println("\nPassenger not found.");
            return;
        }
        String name = InputHelper.getStringInput("Enter new name: ");
        int age = InputHelper.getIntInput("Enter new age: ");
        String contactInfo = InputHelper.getStringInput("Enter new contact info: ");
        Passenger updatedPassenger = new Passenger(passengerId, name, age, contactInfo);
        editPassenger(passengerId, updatedPassenger);
    }

    private void removePassengerMenu() {
        System.out.println();
        String passengerId = InputHelper.getStringInput("Enter passenger ID to remove: ");
        removePassenger(passengerId);
    }
}