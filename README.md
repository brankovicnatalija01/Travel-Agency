# Travel Agency Management System

This project is a **client-server desktop application** developed in Java as part of the **Software Design** course at the Faculty of Organizational Sciences. The application efficiently manages and tracks operations in a travel agency, streamlining the process of handling user records, travel arrangements, and reservations, and providing a structured approach to data management for agency staff.

The project follows a simplified version of **Larman's software development methodology**, covering all key phases from requirements gathering to implementation and testing. You can view the complete project documentation, which includes a detailed description of each phase, in PDF format.

## Technology Overview

- **Programming Language**: Java
- **Database**: MySQL
- **Architecture**: Client-server model with **socket-based communication** 
- **Development Methodology**: Simplified Larman's software development methodology

## Features

- **User Management**: Simplified module for agency staff to add, view, and modify user records.
- **Arrangement Tracking**: Allows efficient creation and updating of travel arrangements, with support for quick data retrieval.
- **Reservation System**: Provides a structured approach to handle reservations, ensuring data consistency and easy access.

### Architecture Overview

- **Client**: The desktop user interface where agency staff interact with the system.
- **Server**: The backend that manages database interactions and handles concurrent client requests using multi-threading.
- **Common Module**: A shared module containing classes and operations that are used by both the client and server, centralizing common logic to ensure code reusability and consistency across the application.

- **Real-Time Client-Server Communication**: Utilizes **sockets** to enable fast, real-time data exchange between the client and server, ensuring synchronized and up-to-date information.
  
- **Concurrent Request Handling**: Uses **threads** and **concurrent programming** principles to process multiple client requests simultaneously, improving the application's performance and responsiveness.

### Application Workflow

#### Server Side

On the server side, the application requires the administrator to start the server before clients can access its features. The interface displays the current server status, showing whether it is running or stopped. The administrator can easily control the server with **Start Server** and **Stop Server** buttons to manage availability as needed.

<p align="center">
  <img src="assets/server form1.png" alt="Server Form" width="600" height = "400"/>
</p>

#### Client Side

On the client side, employees of the travel agency must log into the system by entering their username and password. The login form ensures that only authorized personnel have access to the system's features and data.

<p align="center">
  <img src="assets/login form1.png" alt="Login Form" width="600" height = "400"/>
</p>

Once logged in, employees are presented with the main interface, where they can manage and perform operations on users, travel arrangements, and reservations. Options for each of these actions are accessible through the menu, allowing staff to efficiently navigate and handle various tasks within the system.

<p align="center">
  <img src="assets/main form1.png" alt="Main Form" width="600" height = "400"/>
</p>

One example of the application’s functionality is the creation of reservations. The reservation form allows employees to create a new reservation by entering essential details such as the reservation code, reservation date, room type (e.g., single, double), and selecting a travel arrangement from the list. Each reservation can include multiple room arrangements, where employees can assign users to rooms, select insurance options, and specify the total price for each user. Additional options allow adding or deleting room arrangements, and once all details are set, the reservation can be saved.

<p align="center">
  <img src="assets/create reservation1.png" alt="Create Reservation" width="600" height = "400"/>
</p>

The reservations overview displays a list of all reservations, allowing employees to search for specific reservations by entering the reservation code. Once a reservation is found, clicking the Details button enables the employee to view, edit, or delete the selected reservation. This feature simplifies reservation management by providing quick access to each reservation’s details and options for modification.

<p align="center">
  <img src="assets/search reservation1.png" alt="Search Reservation" width="600" height = "400"/>
</p>

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or later
- NetBeans IDE or any preferred Java IDE for running desktop applications

### Setup Instructions

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/travel-agency-management-system.git
   ```
2. Open the project in your IDE and build it to install dependencies.
3. **Start the server**: Run the server program first to initialize the backend and allow connections.
4. **Start the client**: Once the server is running, launch the client program to access the application interface.

## Documentation

For a detailed description of each phase and feature, view the [Project Documentation](docs/Travel_Agency_Management_Thesis.pdf).
