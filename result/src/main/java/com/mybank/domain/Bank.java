package com.mybank.domain;

import java.util.ArrayList;

/**
 * Represents a banking institution that manages customers and their accounts.
 * Implements the Singleton pattern to ensure only one instance exists.
 *
 * @author Sviatoslav Kalinichuk
 */
public class Bank {
    private static Bank instance;
    private ArrayList<Customer> customers;

    private Bank() {
        customers = new ArrayList<>();
    }

    static {
        instance = new Bank();
    }

    /**
     * Gets the singleton instance of the bank.
     * @return The bank instance
     */
    public static Bank getInstance() {
        return instance;
    }

    /**
     * Adds a new customer to the bank.
     * @param firstName Customer's first name
     * @param lastName Customer's last name
     */
    public static void addCustomer(String firstName, String lastName) {
        instance.customers.add(new Customer(firstName, lastName));
    }

    /**
     * Gets the total number of customers in the bank.
     * @return Number of customers
     */
    public static int getNumberOfCustomers() {
        return instance.customers.size();
    }

    /**
     * Gets a customer by their index in the customer list.
     * @param index Customer index
     * @return Customer object
     */
    public static Customer getCustomer(int index) {
        return instance.customers.get(index);
    }
}
