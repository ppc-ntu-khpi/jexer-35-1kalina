package com.mybank.reporting;

import com.mybank.domain.*;

/**
 * Generates a formatted report of all customers and their accounts.
 * The report includes customer names and their associated account balances.
 *
 * @author Sviatoslav Kalinichuk
 */
public class CustomerReport {

  public CustomerReport() {
  }

  /**
   * Generates and prints a detailed report of all customers and their accounts.
   * The report includes:
   * - Customer names
   * - Account types (Savings or Checking)
   * - Current balance for each account
   */
  public void generateReport() {

    // Display report title and header
    System.out.println("\t\t\tCUSTOMERS REPORT");
    System.out.println("\t\t\t================");

    // Iterate through all customers in the bank
    for ( int cust_idx = 0;
          cust_idx < Bank.getNumberOfCustomers();
          cust_idx++ ) {
      Customer customer = Bank.getCustomer(cust_idx);

      // Display customer's full name
      System.out.println();
      System.out.println("Customer: "
                         + customer.getLastName() + ", "
                         + customer.getFirstName());

      // Process each account owned by the customer
      for ( int acct_idx = 0;
            acct_idx < customer.getNumberOfAccounts();
            acct_idx++ ) {
        Account account = customer.getAccount(acct_idx);
        String  account_type = "";

        // Identify the specific type of account
        if ( account instanceof SavingsAccount ) {
          account_type = "Savings Account";
        } else if ( account instanceof CheckingAccount ) {
          account_type = "Checking Account";
        } else {
          account_type = "Unknown Account Type";
        }

        // Display account type and current balance
        System.out.println("    " + account_type + ": current balance is "
                           + account.getBalance());
      }
    }
  }
}