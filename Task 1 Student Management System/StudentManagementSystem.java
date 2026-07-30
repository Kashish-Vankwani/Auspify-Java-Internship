import java.util.ArrayList;
import java.util.Scanner;

// Step 1: Student Model Class
class Student {
    private int id;
    private String name;
    private double marks;

    // Constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    // Display formatted student details
    public void displayStudent() {
        System.out.println("ID: " + id + " | Name: " + name + " | Marks: " + marks);
    }
}

// Main Application Class
public class StudentManagementSystem {
    // Step 5: Store data using ArrayList
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=================================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    running = false;
                    System.out.println("\nExiting System. Good luck with your internship!");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }

    // Step 2: Functionality to insert student records
    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        // Basic duplicate ID check
        for (Student s : studentList) {
            if (s.getId() == id) {
                System.out.println("Error: A student with ID " + id + " already exists!");
                return;
            }
        }

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student Marks: ");
        double marks = scanner.nextDouble();

        studentList.add(new Student(id, name, marks));
        System.out.println(">> Student added successfully!");
    }

    // Step 3: Display all student details
    private static void viewStudents() {
        System.out.println("\n--- All Student Records ---");
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student s : studentList) {
            s.displayStudent();
        }
    }

    // Step 4: Search student by ID
    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        if (studentList.isEmpty()) {
            System.out.println("No records available to search.");
            return;
        }

        System.out.print("Enter Student ID to search: ");
        int searchId = scanner.nextInt();

        boolean found = false;
        for (Student s : studentList) {
            if (s.getId() == searchId) {
                System.out.println("\nRecord Found:");
                s.displayStudent();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + searchId + " not found.");
        }
    }
}
