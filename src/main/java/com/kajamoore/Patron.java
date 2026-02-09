package com.kajamoore;

/* Patron class represents a library patron.
 * Each patron has a unique 7-digit ID, name, address, and overdue fine.
 */
public class Patron {
    private String id;        // Use String to preserve leading zeros
    private String name;
    private String address;
    private double fine;      // Overdue fine, must be 0-250

    /* A constructor, create a Patron object
     * parameter: id - 7-digit patron ID
     * parameter: name - Patron's full name
     * parameter: address - Patron's address
     * parameter: fine - Current overdue fine
     */
    public Patron(String id, String name, String address, double fine) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fine = fine;
    }

    // Getter for ID
    public String getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Getter for fine
    public double getFine() {
        return fine;
    }


    // Override toString() to display patron information in a readable format
    @Override
    public String toString() {
        return id + " - " + name + " - " + address + " - $" + fine;
    }
}
