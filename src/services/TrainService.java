package services;

import models.Train;
import dsa.TrainLinkedList;
import utils.InputHelper;

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
            System.out.println("0. Back to Main Menu");
            choice = InputHelper.getIntInput("Enter your choice: ");
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
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void addTrainMenu() {
        int trainNumber = InputHelper.getIntInput("Enter train number: ");
        String name = InputHelper.getStringInput("Enter train name: ");
        String route = InputHelper.getStringInput("Enter route: ");
        String timing = InputHelper.getStringInput("Enter timing: ");
        int seatCapacity = InputHelper.getIntInput("Enter seat capacity: ");
        String departureTime = InputHelper.getStringInput("Enter departure time: ");
        Train train = new Train(trainNumber, name, route, timing, seatCapacity, departureTime);
        addTrain(train);
        System.out.println("Train added successfully.");
    }

    private void viewAllTrainsMenu() {
        if (trainList.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }
        System.out.println("--- All Trains ---");
        trainList.forEach(train -> System.out.println(train));
    }

    private void updateTrainMenu() {
        int trainNumber = InputHelper.getIntInput("Enter train number to update: ");
        Train train = viewTrain(trainNumber);
        if (train == null) {
            System.out.println("Train not found.");
            return;
        }
        String name = InputHelper.getStringInput("Enter new train name: ");
        String route = InputHelper.getStringInput("Enter new route: ");
        String timing = InputHelper.getStringInput("Enter new timing: ");
        int seatCapacity = InputHelper.getIntInput("Enter new seat capacity: ");
        String departureTime = InputHelper.getStringInput("Enter new departure time: ");
        train.updateTrain(name, route, timing, seatCapacity, departureTime);
        System.out.println("Train updated successfully.");
    }

    private void deleteTrainMenu() {
        int trainNumber = InputHelper.getIntInput("Enter train number to delete: ");
        Train train = viewTrain(trainNumber);
        if (train == null) {
            System.out.println("Train not found.");
            return;
        }
        deleteTrain(trainNumber);
        System.out.println("Train deleted successfully.");
    }
}