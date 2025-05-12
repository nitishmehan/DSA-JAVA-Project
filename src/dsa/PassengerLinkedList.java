package dsa;

import models.Passenger;
import java.util.function.Consumer;

public class PassengerLinkedList {
    private Node head;
    private int size;

    private static class Node {
        Passenger data;
        Node next;

        Node(Passenger data) {
            this.data = data;
            this.next = null;
        }
    }

    public PassengerLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(Passenger data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void remove(Passenger data) {
        if (head == null) return;

        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    public Passenger get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void forEach(Consumer<Passenger> action) {
        Node current = head;
        while (current != null) {
            action.accept(current.data);
            current = current.next;
        }
    }
}