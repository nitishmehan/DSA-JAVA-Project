# Railway Booking Management System

## Overview
The Railway Booking Management System is a console-based Java application designed to manage train operations, passenger registrations, and ticket bookings. This project demonstrates the application of key data structures and algorithms, including linked lists, binary search trees, queues, and sorting algorithms.

## Features
- **Train Management**: Add, view, update, and delete train information, including train number, name, route, timing, and seat capacity.
- **Passenger Management**: Register, view, edit, and remove passenger details, including ID, name, age, and contact information.
- **Booking System**: Book tickets if seats are available, manage a waiting list using a queue, and cancel bookings while automatically reassigning waitlisted passengers.
- **Search Functionality**: Efficiently find trains by number or name using a binary search tree.
- **Sorting Algorithms**: Sort and display trains by departure time, name, or route using custom sorting implementations.

## Project Structure
```
RailwayBookingManagementSystem
├── src
│   ├── Main.java
│   ├── models
│   │   ├── Train.java
│   │   ├── Passenger.java
│   │   └── Booking.java
│   ├── dsa
│   │   ├── LinkedList.java
│   │   ├── Queue.java
│   │   ├── BST.java
│   │   └── SortUtils.java
│   ├── services
│   │   ├── TrainService.java
│   │   ├── PassengerService.java
│   │   └── BookingService.java
│   └── utils
│       └── InputHelper.java
├── README.md
```

## Setup Instructions
1. Clone the repository or download the project files.
2. Open the project in your preferred Java IDE.
3. Ensure you have Java Development Kit (JDK) installed on your machine.
4. Compile the Java files in the `src` directory.
5. Run the `Main.java` file to start the application.

## Usage Guidelines
- Follow the on-screen menu to navigate through the system.
- Input the required details as prompted for managing trains, passengers, and bookings.
- Utilize the search and sorting features to efficiently manage and view data.

## Conclusion
This Railway Booking Management System serves as a practical application of Java programming and data structure concepts, providing a comprehensive solution for managing railway operations.