package services;

import models.Train;
import dsa.TrainLinkedList;
import utils.InputHelper;
import dsa.BST;

public class TrainService {
    private TrainLinkedList trainList;

    public TrainService() {
        trainList = new TrainLinkedList();
    }

    public void addTrain(Train train) {
        trainList.add(train);
    }

    public Train viewTrain(int trainNumber) {
        for (int i = 0; i < trainList.size(); i++) {
            Train train = trainList.get(i);
            if (train.getTrainNumber() == trainNumber) {
                return train;
            }
        }
        return null; // Train not found
    }

    public void updateTrain(int trainNumber, Train updatedTrain) {
        for (int i = 0; i < trainList.size(); i++) {
            Train train = trainList.get(i);
            if (train.getTrainNumber() == trainNumber) {
                trainList.remove(train);
                trainList.add(updatedTrain);
                return;
            }
        }
    }

    public void deleteTrain(int trainNumber) {
        for (int i = 0; i < trainList.size(); i++) {
            Train train = trainList.get(i);
            if (train.getTrainNumber() == trainNumber) {
                trainList.remove(train);
                return;
            }
        }
    }

    public TrainLinkedList getAllTrains() {
        return trainList;
    }

    public void manageTrains() {
        int choice;
        do {
            System.out.println("\n--- Train Management ---");
            System.out.println("1. Add Train");
            System.out.println("2. View All Trains");
            System.out.println("3. Update Train");
            System.out.println("4. Delete Train");
            System.out.println("5. Display Trains Sorted By");
            System.out.println("6. Find Train By Name (BST)");
            System.out.println("0. Back to Main Menu");
            choice = InputHelper.getIntInput("Enter your choice: ");
            System.out.println();
            switch (choice) {
                case 1:
                    addTrainMenu();
                    break;
                case 2:
                    viewAllTrainsMenu();
                    break;
                case 3:
                    updateTrainMenu();
                    break;
                case 4:
                    deleteTrainMenu();
                    break;
                case 5:
                    displayTrainsSortedMenu();
                    break;
                case 6:
                    findTrainByNameMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nInvalid choice.");
            }
        } while (choice != 0);
    }

    private void addTrainMenu() {
        System.out.println();
        int trainNumber = InputHelper.getIntInput("Enter train number: ");
        String name = InputHelper.getStringInput("Enter train name: ");
        String route = InputHelper.getStringInput("Enter route: ");
        String timing = InputHelper.getStringInput("Enter timing: ");
        int seatCapacity = InputHelper.getIntInput("Enter seat capacity: ");
        String departureTime = InputHelper.getStringInput("Enter departure time: ");
        Train train = new Train(trainNumber, name, route, timing, seatCapacity, departureTime);
        addTrain(train);
        System.out.println("\nTrain added successfully.");
    }

    public void viewAllTrainsMenu() {
        System.out.println();
        if (trainList.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }
        System.out.println("--- All Trains ---");
        printTrainTableHeader();
        trainList.forEach(this::printTrainRow);
        printTrainTableFooter();
    }

    private void updateTrainMenu() {
        System.out.println();
        int trainNumber = InputHelper.getIntInput("Enter train number to update: ");
        Train train = viewTrain(trainNumber);
        if (train == null) {
            System.out.println("\nTrain not found.");
            return;
        }
        String name = InputHelper.getStringInput("Enter new train name: ");
        String route = InputHelper.getStringInput("Enter new route: ");
        String timing = InputHelper.getStringInput("Enter new timing: ");
        int seatCapacity = InputHelper.getIntInput("Enter new seat capacity: ");
        String departureTime = InputHelper.getStringInput("Enter new departure time: ");
        train.updateTrain(name, route, timing, seatCapacity, departureTime);
        System.out.println("\nTrain updated successfully.");
    }

    private void deleteTrainMenu() {
        System.out.println();
        int trainNumber = InputHelper.getIntInput("Enter train number to delete: ");
        Train train = viewTrain(trainNumber);
        if (train == null) {
            System.out.println("\nTrain not found.");
            return;
        }
        deleteTrain(trainNumber);
        System.out.println("\nTrain deleted successfully.");
    }

    public void displayTrainsSortedMenu() {
        System.out.println();
        if (trainList.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }
        System.out.println("--- Display Trains Sorted By ---");
        System.out.println("1. Name");
        System.out.println("2. Route");
        System.out.println("3. Departure Time");
        int sortChoice = InputHelper.getIntInput("Enter your choice: ");
        int n = trainList.size();
        models.Train[] trains = new models.Train[n];
        for (int i = 0; i < n; i++) {
            trains[i] = trainList.get(i);
        }
        switch (sortChoice) {
            case 1:
                dsa.SortUtils.sortByName(trains);
                System.out.println("\n--- Trains Sorted by Name ---");
                break;
            case 2:
                dsa.SortUtils.sortByRoute(trains);
                System.out.println("\n--- Trains Sorted by Route ---");
                break;
            case 3:
                dsa.SortUtils.bubbleSort(trains);
                System.out.println("\n--- Trains Sorted by Departure Time ---");
                break;
            default:
                System.out.println("\nInvalid choice.");
                return;
        }
        printTrainTableHeader();
        for (models.Train train : trains) {
            printTrainRow(train);
        }
        printTrainTableFooter();
    }

    public void findTrainByNameMenu() {
        System.out.println();
        if (trainList.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }
        String name = InputHelper.getStringInput("Enter train name to search: ");
        BST bst = buildBSTFromTrainList();
        models.Train found = bst.searchByName(name);
        if (found != null) {
            System.out.println("\nTrain found:");
            printTrainTableHeader();
            printTrainRow(found);
            printTrainTableFooter();
        } else {
            System.out.println("\nTrain not found.");
        }
    }

    private BST buildBSTFromTrainList() {
        BST bst = new BST();
        for (int i = 0; i < trainList.size(); i++) {
            bst.insert(trainList.get(i));
        }
        return bst;
    }

    private void printTrainTableHeader() {
        System.out.println("+----------+---------------------+---------------------+----------+--------------+----------------+");
        System.out.printf("| %-8s | %-19s | %-19s | %-8s | %-12s | %-14s |\n", 
            "Number", "Name", "Route", "Timing", "Seats (A/T)", "Departure");
        System.out.println("+----------+---------------------+---------------------+----------+--------------+----------------+");
    }

    private void printTrainRow(models.Train train) {
        System.out.printf("| %-8d | %-19s | %-19s | %-8s | %4d/%-7d | %-14s |\n",
            train.getTrainNumber(),
            train.getName(),
            train.getRoute(),
            train.getTiming(),
            train.getAvailableSeats(),
            train.getSeatCapacity(),
            train.getDepartureTime());
    }

    private void printTrainTableFooter() {
        System.out.println("+----------+---------------------+---------------------+----------+--------------+----------------+");
    }
}