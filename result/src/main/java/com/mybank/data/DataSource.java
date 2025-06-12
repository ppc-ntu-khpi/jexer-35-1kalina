/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.data;

import com.mybank.domain.Bank;
import com.mybank.domain.CheckingAccount;
import com.mybank.domain.Customer;
import com.mybank.domain.SavingsAccount;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Data source handler for loading customer and account information from a file.
 * Reads customer data and their associated accounts from a structured text file.
 *
 * @author Sviatoslav Kalinichuk
 */
public class DataSource {

    private File dataFile;

    /**
     * Creates a new data source with the specified file path.
     * @param dataFilePath Path to the data file
     */
    public DataSource(String dataFilePath) {
        this.dataFile = new File(dataFilePath);
    }

    /**
     * Loads customer and account data from the data file.
     * File format:
     * - First line: Number of customers
     * - For each customer:
     *   - First name
     *   - Last name
     *   - Number of accounts
     *   - For each account:
     *     - Account type (S for Savings, C for Checking)
     *     - Initial balance
     *     - Interest rate (for Savings) or overdraft protection (for Checking)
     * 
     * @throws IOException if the data file cannot be read or has invalid format
     */
    public void loadData() throws IOException {
        // Validate data file existence
        if (!dataFile.exists()) {
            throw new IOException("Data file not found: " + dataFile.getAbsolutePath());
        }
        
        System.out.println("Reading data from: " + dataFile.getAbsolutePath()); // Log data source location
        Scanner input = new Scanner(dataFile);
        
        // Initialize data loading
        Customer customer;
        int numOfCustomers = input.nextInt();
        System.out.println("Found " + numOfCustomers + " customers"); // Log customer count
        
        for (int idx = 0; idx < numOfCustomers; idx++) {
            // Load customer information
            String firstName = input.next();
            String lastName = input.next();
            System.out.println("Loading customer: " + firstName + " " + lastName); // Log customer details
            
            Bank.addCustomer(firstName, lastName);
            customer = Bank.getCustomer(idx);
            
            // Load customer's accounts
            int numOfAccounts = input.nextInt();
            System.out.println("Customer has " + numOfAccounts + " accounts"); // Log account count
            
            while (numOfAccounts-- > 0) {
                // Process account information based on type
                char accountType = input.next().charAt(0);
                switch (accountType) {
                    // Process savings account
                    case 'S': {
                        float initBalance = input.nextFloat();
                        float interestRate = input.nextFloat();
                        System.out.println("Adding savings account with balance: " + initBalance + ", interest: " + interestRate); // Log account details
                        customer.addAccount(new SavingsAccount(initBalance, interestRate));
                        break;
                    }
                    // Process checking account
                    case 'C': {
                        float initBalance = input.nextFloat();
                        float overdraftProtection = input.nextFloat();
                        System.out.println("Adding checking account with balance: " + initBalance + ", overdraft: " + overdraftProtection); // Log account details
                        customer.addAccount(new CheckingAccount(initBalance, overdraftProtection));
                        break;
                    }
                }
            }
        }
        input.close();
    }
}
