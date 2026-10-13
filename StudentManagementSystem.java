import java.util.ArrayList;
import java.util.Scanner;

// Represents one student
class Student {
    String matricNumber;
    String fullName;
    String programme;
    int yearLevel;

    // Constructor: initializes a student's details
    Student(String matricNumber, String fullName,
            String programme, int yearLevel) {
        this.matricNumber = matricNumber;
        this.fullName = fullName;
        this.programme = programme;
        this.yearLevel = yearLevel;
    }

    // Displays the student's information
    void displayDetails() {
        System.out.println("Matric Number: " + matricNumber);
        System.out.println("Full Name: " + fullName);
        System.out.println("Programme: " + programme);
        System.out.println("Year Level: " + yearLevel);
        System.out.println("-----------------------------");
    }
}

// Main class
public class StudentManagementSystem {

    // Stores student objects while the program is running
    static ArrayList<Student> students = new ArrayList<>();

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = 0;

        // Keep displaying the menu until the user exits
        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Read the choice safely
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

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
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println("Program closed. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);

        input.close();
    }

    // Adds a new student
    static void addStudent() {
        System.out.print("Enter matric number: ");
        String matric = input.nextLine().trim();

        if (matric.isEmpty()) {
            System.out.println("Matric number cannot be empty.");
            return;
        }

        // Prevent duplicate matric numbers
        if (findStudent(matric) != null) {
            System.out.println("A student with this matric number already exists.");
            return;
        }

        System.out.print("Enter full name: ");
        String name = input.nextLine().trim();

        System.out.print("Enter programme: ");
        String programme = input.nextLine().trim();

        System.out.print("Enter year level: ");
        int year;

        try {
            year = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid year level.");
            return;
        }

        if (name.isEmpty() || programme.isEmpty()
                || year < 1 || year > 6) {
            System.out.println("Enter valid student details.");
            return;
        }

        Student student = new Student(matric, name, programme, year);
        students.add(student);

        System.out.println("Student added successfully!");
    }

    // Displays every student
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n===== ALL STUDENTS =====");

        for (Student student : students) {
            student.displayDetails();
        }
    }

    // Searches for a student using the matric number
    static void searchStudent() {
        System.out.print("Enter matric number to search: ");
        String matric = input.nextLine().trim();

        Student student = findStudent(matric);

        if (student != null) {
            System.out.println("\nStudent found:");
            student.displayDetails();
        } else {
            System.out.println("Student not found.");
        }
    }

    // Updates the name, programme and year level
    static void updateStudent() {
        System.out.print("Enter matric number to update: ");
        String matric = input.nextLine().trim();

        Student student = findStudent(matric);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new full name: ");
        String name = input.nextLine().trim();

        System.out.print("Enter new programme: ");
        String programme = input.nextLine().trim();

        System.out.print("Enter new year level: ");
        int year;

        try {
            year = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid year level.");
            return;
        }

        if (name.isEmpty() || programme.isEmpty()
                || year < 1 || year > 6) {
            System.out.println("Enter valid student details.");
            return;
        }

        student.fullName = name;
        student.programme = programme;
        student.yearLevel = year;

        System.out.println("Student updated successfully!");
    }

    // Deletes a student record
    static void deleteStudent() {
        System.out.print("Enter matric number to delete: ");
        String matric = input.nextLine().trim();

        Student student = findStudent(matric);

        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Reusable method to find a student by matric number
    static Student findStudent(String matric) {
        for (Student student : students) {
            if (student.matricNumber.equalsIgnoreCase(matric)) {
                return student;
            }
        }

        return null;
    }
}