package com.kajamoore;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * Library class manages a collection of patrons.
 * Provides methods to add, remove, display, and load patrons from a file.
 */
public class Library {

    private ArrayList<Patron> patrons; // List of all patrons in the system


    // Constructor initializes the patrons list
    public Library() {
        patrons = new ArrayList<>();
    }

    /*
     * Validate that an ID is exactly 7 digits
     * parameter: id - ID to check
     * return: true if valid, false otherwise
     */
    public boolean isValidId(String id) {
        return id.matches("\\d{7}");
    }

    /* This validates that fine is within allowed range 0-250
     * parameter: fine - Fine amount to check
     * return: true if valid, false otherwise
     */
    public boolean isValidFine(double fine) {
        return fine >= 0 && fine <= 250;
    }

    /* Add a patron to the list
     * parameter: patron - Patron object to add
     */
    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    /* This Loads patrons from a text file
     * Each line must have: ID,Name,Address, and Fine
     * Invalid lines are skipped
     * parameter: filename - File path
     */
    public void loadPatronsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 1;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");

                // This skip lines that don't have exactly 4 parts
                if (parts.length != 4) {
                    System.out.println("Skipping invalid line " + lineNum + ": " + line);
                    lineNum++;
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();
                String address = parts[2].trim();
                String fineStr = parts[3].trim();

                // Validates ID
                if (!isValidId(id)) {
                    System.out.println("Invalid ID skipped on line " + lineNum + ": " + id);
                    lineNum++;
                    continue;
                }

                // Validates fine
                /* This will convert the string input to a double, but if conversion fails it
                 * will handle the error.
                 */
                double fine;
                try {
                    fine = Double.parseDouble(fineStr);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid fine skipped on line " + lineNum + ": " + fineStr);
                    lineNum++; // Increments the line number to keep track of file reading.
                    continue; // Skips the processing line and continues with the next line in the loop.
                }

                // This checks the range of the fine making sure it's within.
                if (!isValidFine(fine)) {
                    System.out.println("Fine out of range skipped on line " + lineNum + ": " + fine);
                    lineNum++;
                    continue;
                }

                // Create Patron object to add to the list
                addPatron(new Patron(id, name, address, fine));
                lineNum++;
            }

            System.out.println("Patrons loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /* We are to remove the patron by ID
     * parameter: id - Patron ID
     */
    public void removePatronById(String id) {
        boolean removed = patrons.removeIf(p -> p.getId().equals(id));
        if (!removed) {
            System.out.println("No patron found with ID: " + id);
        }
    }

    // Must display all patrons in the system

    public void displayAllPatrons() {
        if (patrons.isEmpty()) {
            System.out.println("No patrons in the system.");
            return;
        }

        for (Patron p : patrons) {
            System.out.println(p);
        }
    }
}
