package com.mybank.domain;

import java.util.ArrayList;

/**
 * Represents a bank customer with personal information and associated accounts.
 *
 * @author Sviatoslav Kalinichuk
 */
public class Customer {
    private final String firstName;
    private final String lastName;
    private ArrayList<Account> accounts;

    /**
     * Creates a new customer with the specified name.
     * @param firstName Customer's first name
     * @param lastName Customer's last name
     */
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = new ArrayList<>();
    }

    /**
     * Gets the customer's first name.
     * @return First name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the customer's last name.
     * @return Last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets an account by its index in the customer's account list.
     * @param index Account index
     * @return Account object
     */
    public Account getAccount(int index) {
        return accounts.get(index);
    }

    /**
     * Adds a new account to the customer's account list.
     * @param account Account to add
     */
    public void addAccount(Account account) {
        accounts.add(account);
    }

    /**
     * Gets the total number of accounts owned by the customer.
     * @return Number of accounts
     */
    public int getNumberOfAccounts() {
        return accounts.size();
    }
}
