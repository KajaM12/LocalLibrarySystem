/**
 * Kaja Moore
 * CEN 3024C Software Development I
 * 02/08/2026
 * LibrarySystem.java
 * This application is to provide a simple consoled based Library Management System that allows
 * the Librarians (users) to manage a library's list of patrons easily. The system is designed to
 * make it easy to interact with the system, add, remove, and view all patrons.
 *
*/

/* NOTE: when searching for JAR input "java -cp .\LibrarySystem-1.0-SNAPSHOT.jar com.kajamoore.Main"
 * The java "-jar .\LibrarySystem-1.0-SNAPSHOT.jar" will not open it.
 * Found another way through the video that was provided through resources
 * https://www.youtube.com/watch?v=870XIYMrlSo
 */

package com.kajamoore;

import java.util.Scanner;

    /* My main class for the Library Management System.
    * A console-based menu to interact with the system.
    */
public class Main {

    public static void main(String[] args) {
        Library library = new Library(); // Create library object
        Scanner scanner = new Scanner(System.in);
        boolean running = true; // Controls the program loop

        while (running) {
            /* Displays menu options that we need to Add, Load from files, Remove, Display Patrons and also
            * the option to exit the menu.
             */

            System.out.println("\n--- Library System ---");
            System.out.println("1. Add Patron Manually");
            System.out.println("2. Load Patrons from File");
            System.out.println("3. Remove Patron");
            System.out.println("4. Display All Patrons");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            // Reads user input and handle invalid input
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number 1-5.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Adds a new patron manually
                    System.out.print("Enter 7-digit ID: ");
                    String id = scanner.nextLine();

                    // This will validate the entered ID using the library's isValID method
                    if (!library.isValidId(id)) {
                        System.out.println("Invalid ID. Must be exactly 7 digits.");
                        break;
                    }

                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();

                    System.out.print("Enter overdue fine: ");
                    double fine;

                    /* By using the try-catch method, this will convert the input to a double. if
                     * and catch the invalid numeric input. The exit is will return to the menu screen
                     * if it's not a valid number.
                     */
                    try {

                        fine = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid fine. Must be a number between 0-250.");
                        break;
                    }

                    //Validates the fine amound using the lirbary's isValidFine method.
                    if (!library.isValidFine(fine)) {
                        System.out.println("Fine out of range. Must be between $0 and $250.");
                        break;
                    }

                    // Adds patron to library
                    library.addPatron(new Patron(id, name, address, fine));
                    System.out.println("Patron added successfully.");
                    break;

                case 2:
                    // Loads patrons from file
                    System.out.print("Enter file name (must type patrons.txt to load file, then choose option 4 to display): ");
                    String filename = scanner.nextLine();
                    library.loadPatronsFromFile(filename);
                    break;

                case 3:
                    // Removes patron by ID
                    System.out.print("Enter Patron ID to remove: ");
                    String removeId = scanner.nextLine();
                    library.removePatronById(removeId);
                    break;

                case 4:
                    // Displays all patrons
                    library.displayAllPatrons();
                    break;

                case 5:
                    // Exits program
                    running = false;
                    System.out.println("Exiting system.");
                    break;

                default:
                    System.out.println("Invalid option. Choose 1-5."); // Prompts the use for a correct input.
            }
        }

        scanner.close(); // Close scanner when done
    }
}
