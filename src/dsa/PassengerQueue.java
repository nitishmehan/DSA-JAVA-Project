package dsa;

import models.Passenger;

public class PassengerQueue {
    private Node front;
    private Node rear;
    private int size;

    private static class Node {
        Passenger data;
        int trainNumber;
        Node next;

        Node(Passenger data, int trainNumber) {
            this.data = data;
            this.trainNumber = trainNumber;
            this.next = null;
        }
    }

    public PassengerQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Modified enqueue to accept trainNumber
    public void enqueue(Passenger data, int trainNumber) {
        Node newNode = new Node(data, trainNumber);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
        size++;
    }

    // Modified dequeue to return a wrapper object with both passenger and trainNumber
    public PassengerTrainPair dequeue() {
        if (isEmpty()) {
            return null;
        }
        Passenger data = front.data;
        int trainNumber = front.trainNumber;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return new PassengerTrainPair(data, trainNumber);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Passenger peek() {
        return isEmpty() ? null : front.data;
    }

    public int peekTrainNumber() {
        return isEmpty() ? -1 : front.trainNumber;
    }

    public void display() {
        Node current = front;
        while (current != null) {
            System.out.println("Train No: " + current.trainNumber + ", " + current.data);
            current = current.next;
        }
    }

    public void forEach(java.util.function.BiConsumer<Passenger, Integer> action) {
        Node current = front;
        while (current != null) {
            action.accept(current.data, current.trainNumber);
            current = current.next;
        }
    }

    // Helper class to return both passenger and train number
    public static class PassengerTrainPair {
        public final Passenger passenger;
        public final int trainNumber;
        public PassengerTrainPair(Passenger passenger, int trainNumber) {
            this.passenger = passenger;
            this.trainNumber = trainNumber;
        }
    }
}
