package dsa;

import models.Train;

public class SortUtils {

    public static void bubbleSort(Train[] trains) {
        int n = trains.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (trains[j].getDepartureTime().compareTo(trains[j + 1].getDepartureTime()) > 0) {
                    // Swap trains[j] and trains[j + 1]
                    Train temp = trains[j];
                    trains[j] = trains[j + 1];
                    trains[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(Train[] trains) {
        int n = trains.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (trains[j].getDepartureTime().compareTo(trains[minIndex].getDepartureTime()) < 0) {
                    minIndex = j;
                }
            }
            Train temp = trains[minIndex];
            trains[minIndex] = trains[i];
            trains[i] = temp;
        }
    }

    public static void sortByName(Train[] trains) {
        int n = trains.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (trains[j].getName().compareTo(trains[j + 1].getName()) > 0) {
                    Train temp = trains[j];
                    trains[j] = trains[j + 1];
                    trains[j + 1] = temp;
                }
            }
        }
    }

    public static void sortByRoute(Train[] trains) {
        int n = trains.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (trains[j].getRoute().compareTo(trains[j + 1].getRoute()) > 0) {
                    Train temp = trains[j];
                    trains[j] = trains[j + 1];
                    trains[j + 1] = temp;
                }
            }
        }
    }
}