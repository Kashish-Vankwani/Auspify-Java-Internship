import java.util.ArrayList;
import java.util.Scanner;

// Step 1: Book Class (OOP Concept)
class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false; // By default, book is available
    }

    // Getters and Setters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    public void displayBook() {
        String status = isIssued ? "ISSUED" : "AVAILABLE";
        System.out.println("ID: " + bookId + " | Title: " + title + " | Author: " + author + " | Status: " + status);
    }
}

// Main Application Class
public class LibraryManagementSystem {
    private static ArrayList<Book> library = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=================================");
            System.out.println("   LIBRARY MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add New Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Search Book (by ID/Title)");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    displayBooks();
                    break;
                case 4:
                    searchBook();
                    break;
                case 5:
                    issueBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting System. Good luck with your task!");
                    break;
                default:
                    System.out.println("Invalid option! Please enter 1-7.");
            }
        }
        scanner.close();
    }

    // Step 2: Add Book
    private static void addBook() {
        System.out.println("\n--- Add New Book ---");
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Book b : library) {
            if (b.getBookId() == id) {
                System.out.println("Error: Book with ID " + id + " already exists!");
                return;
            }
        }

        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();

        library.add(new Book(id, title, author));
        System.out.println(">> Book added successfully!");
    }

    // Step 2: Remove Book
    private static void removeBook() {
        System.out.println("\n--- Remove Book ---");
        if (library.isEmpty()) {
            System.out.println("Library is empty!");
            return;
        }

        System.out.print("Enter Book ID to remove: ");
        int id = scanner.nextInt();

        boolean removed = library.removeIf(b -> b.getBookId() == id);
        if (removed) {
            System.out.println(">> Book removed successfully!");
        } else {
            System.out.println("Book with ID " + id + " not found!");
        }
    }

    // Step 3: Display Books
    private static void displayBooks() {
        System.out.println("\n--- All Books in Library ---");
        if (library.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        for (Book b : library) {
            b.displayBook();
        }
    }

    // Step 4: Search Books by Title or ID
    private static void searchBook() {
        System.out.println("\n--- Search Book ---");
        if (library.isEmpty()) {
            System.out.println("Library is empty!");
            return;
        }

        System.out.print("Search by (1) ID or (2) Title: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        if (option == 1) {
            System.out.print("Enter Book ID: ");
            int id = scanner.nextInt();
            for (Book b : library) {
                if (b.getBookId() == id) {
                    b.displayBook();
                    found = true;
                    break;
                }
            }
        } else if (option == 2) {
            System.out.print("Enter Book Title/Keyword: ");
            String keyword = scanner.nextLine().toLowerCase();
            for (Book b : library) {
                if (b.getTitle().toLowerCase().contains(keyword)) {
                    b.displayBook();
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No matching book found.");
        }
    }

    // Step 5: Issue Book
    private static void issueBook() {
        System.out.println("\n--- Issue Book ---");
        System.out.print("Enter Book ID to issue: ");
        int id = scanner.nextInt();

        for (Book b : library) {
            if (b.getBookId() == id) {
                if (b.isIssued()) {
                    System.out.println("Book is ALREADY issued to someone else!");
                } else {
                    b.setIssued(true);
                    System.out.println(">> Book issued successfully!");
                }
                return;
            }
        }
        System.out.println("Book ID not found!");
    }

    // Step 5: Return Book
    private static void returnBook() {
        System.out.println("\n--- Return Book ---");
        System.out.print("Enter Book ID to return: ");
        int id = scanner.nextInt();

        for (Book b : library) {
            if (b.getBookId() == id) {
                if (!b.isIssued()) {
                    System.out.println("This book was NOT issued!");
                } else {
                    b.setIssued(false);
                    System.out.println(">> Book returned successfully!");
                }
                return;
            }
        }
        System.out.println("Book ID not found!");
    }
}