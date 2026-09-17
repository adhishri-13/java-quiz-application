# Library Management System

A simple console-based Library Management System developed using Java.

This project allows users to manage books in a library through a menu-driven console interface. It demonstrates fundamental Java programming concepts such as Object-Oriented Programming (OOP), classes, objects, constructors, methods, conditional statements, loops, switch-case, and ArrayList.

## Features

The application provides the following options:

1. Add a new book
2. Display all books
3. Search for a book by title
4. Issue a book
5. Return a book
6. Delete a book
7. Exit the application

### 1. Add Book
Users can add a new book by entering:
- Book title
- Author name

Each newly added book is automatically assigned a unique Book ID.

### 2. Display All Books
Displays all books currently stored in the library along with:
- Book ID
- Title
- Author
- Availability status

The status is displayed as either `Available` or `Issued`.

### 3. Search Book
Users can search for a book by entering its complete title or part of the title.

The search is case-insensitive.

### 4. Issue Book
Users can issue a book by entering its Book ID.

A book cannot be issued if it is already issued.

### 5. Return Book
Users can return a previously issued book using its Book ID.

A book that has not been issued cannot be returned.

### 6. Delete Book
Users can delete a book from the library by entering its Book ID.

### 7. Exit
The user can exit the application by selecting option 7.

## Technologies Used

- Java
- Java Development Kit (JDK)
- ArrayList
- Scanner
- Command Line / Terminal

## Requirements

Before running the project, make sure the following are installed:

- Java Development Kit (JDK) 8 or later
- A Java-compatible IDE or code editor (optional)
- Command Prompt / Terminal

No external libraries or third-party dependencies are required.

## Project Structure

```text
LibraryManagementSystem/
│
├── LibraryManagementSystem.java
└── README.md
