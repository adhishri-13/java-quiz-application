import java.util.ArrayList;
import java.util.Scanner;

/**
 * ==============================================================
 * LIBRARY MANAGEMENT SYSTEM
 * ==============================================================
 * A simple console-based Java project.
 *
 * Features:
 * 1. Add a new book
 * 2. Display all books
 * 3. Search for a book by title
 * 4. Issue a book
 * 5. Return a book
 * 6. Delete a book
 * 7. Exit
 *
 * Concepts used (as required):
 * - Keywords: class, public, private, static, void, new, if, else,
 *   for, while, switch, case, break, default, return,
 *   true, false, int, String, boolean
 * - Control flow: if-else statements, switch-case
 * - Loops: for loop, while loop
 * - OOP: class, object, constructor, methods
 * - Collections: ArrayList
 * ==============================================================
 */

// ---------- Book class (represents one book record) ----------
class Book {
    private int id;
    private String title;
    private String author;
    private boolean issued; // true if book is currently issued

    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false; // by default, a new book is available
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    // Displays one book's details in a readable format
    public void display() {
        // if-else used to decide the status text
        String status;
        if (issued) {
            status = "Issued";
        } else {
            status = "Available";
        }
        System.out.println(id + "\t" + title + "\t\t" + author + "\t\t" + status);
    }
}

// ---------- Main class (menu-driven program) ----------
public class LibraryManagementSystem {

    // ArrayList to store all books
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static int nextId = 1; // auto-generated book id

    public static void main(String[] args) {

        // Pre-load a few sample books using a for loop
        String[] sampleTitles = {"Java Basics", "Data Structures", "Operating Systems"};
        String[] sampleAuthors = {"J. Smith", "A. Kumar", "R. Verma"};

        for (int i = 0; i < sampleTitles.length; i++) {
            books.add(new Book(nextId, sampleTitles[i], sampleAuthors[i]));
            nextId++;
        }

        boolean running = true; // controls the main while loop

        // Main menu loop -> while loop
        while (running) {
            printMenu();
            int choice = -1;

            // Validate numeric input using if-else
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Invalid input! Please enter a number.\n");
                sc.next(); // discard invalid token
                continue; // skip to next loop iteration
            }

            // switch-case to handle each menu option
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    deleteBook();
                    break;
                case 7:
                    running = false; // this will stop the while loop
                    System.out.println("Thank you for using the Library Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option (1-7).\n");
            }
        }

        sc.close();
    }

    // Prints the main menu
    private static void printMenu() {
        System.out.println("=========================================");
        System.out.println("        LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=========================================");
        System.out.println("1. Add Book");
        System.out.println("2. Display All Books");
        System.out.println("3. Search Book by Title");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Delete Book");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    // 1. Add a new book to the list
    private static void addBook() {
        sc.nextLine(); // clear buffer
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();

        // Basic validation using if-else
        if (title.trim().isEmpty() || author.trim().isEmpty()) {
            System.out.println("Title/Author cannot be empty! Book not added.\n");
        } else {
            Book newBook = new Book(nextId, title, author);
            books.add(newBook);
            nextId++;
            System.out.println("Book added successfully!\n");
        }
    }

    // 2. Display all books using a for loop
    private static void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.\n");
            return;
        }

        System.out.println("\nID\tTitle\t\t\tAuthor\t\tStatus");
        System.out.println("-------------------------------------------------------------");

        // for loop to go through every book
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            b.display();
        }
        System.out.println();
    }

    // 3. Search a book by title using a while loop
    private static void searchBook() {
        sc.nextLine(); // clear buffer
        System.out.print("Enter title (or part of it) to search: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        int index = 0;

        // while loop used to search through the list
        while (index < books.size()) {
            Book b = books.get(index);
            if (b.getTitle().toLowerCase().contains(keyword)) {
                b.display();
                found = true;
            }
            index++;
        }

        // if-else to report the final result
        if (!found) {
            System.out.println("No book found matching: " + keyword);
        }
        System.out.println();
    }

    // 4. Issue a book by ID
    private static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        Book target = findBookById(id);

        if (target == null) {
            System.out.println("Book with ID " + id + " not found.\n");
        } else if (target.isIssued()) {
            System.out.println("Sorry, this book is already issued.\n");
        } else {
            target.setIssued(true);
            System.out.println("Book \"" + target.getTitle() + "\" issued successfully!\n");
        }
    }

    // 5. Return a book by ID
    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        Book target = findBookById(id);

        if (target == null) {
            System.out.println("Book with ID " + id + " not found.\n");
        } else if (!target.isIssued()) {
            System.out.println("This book was not issued.\n");
        } else {
            target.setIssued(false);
            System.out.println("Book \"" + target.getTitle() + "\" returned successfully!\n");
        }
    }

    // 6. Delete a book by ID
    private static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();

        Book target = findBookById(id);

        if (target == null) {
            System.out.println("Book with ID " + id + " not found.\n");
        } else {
            books.remove(target);
            System.out.println("Book deleted successfully!\n");
        }
    }

    // Helper method: finds a book by its ID using a for loop
    private static Book findBookById(int id) {
        Book result = null;

        for (Book b : books) { // enhanced for loop
            if (b.getId() == id) {
                result = b;
                break; // stop as soon as we find it
            }
        }
        return result;
    }
}
