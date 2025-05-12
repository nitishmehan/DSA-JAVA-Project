package dsa;

import models.Passenger;

public class PassengerQueue {
    private Node front;
    private Node rear;
    private int size;

    private static class Node {
        Passenger data;
        Node next;

        Node(Passenger data) {
            this.data = data;
            this.next = null;
        }
    }

    public PassengerQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(Passenger data) {
        Node newNode = new Node(data);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
        size++;
    }

    public Passenger dequeue() {
        if (isEmpty()) {
            return null;
        }
        Passenger data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
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

    public void display() {
        Node current = front;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
