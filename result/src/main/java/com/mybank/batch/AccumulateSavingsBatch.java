package com.mybank.batch;

import com.mybank.domain.*;

/**
 * Batch processor for accumulating interest on savings accounts.
 * Processes all customers' savings accounts and applies interest calculations.
 *
 * @author Sviatoslav Kalinichuk
 */
public class AccumulateSavingsBatch {

  public AccumulateSavingsBatch() {
  }

  /**
   * Processes all savings accounts in the bank and accumulates interest.
   * For each customer's savings account, applies the configured interest rate
   * to the current balance.
   */
  public void doBatch() {
    Bank bank = Bank.getInstance();
    // Process each customer in the bank
    for (int cust_idx = 0;
         cust_idx < bank.getNumberOfCustomers();
         cust_idx++) {
      Customer customer = bank.getCustomer(cust_idx);

      // Process each account owned by the customer
      for (int acct_idx = 0;
           acct_idx < customer.getNumberOfAccounts();
           acct_idx++) {
        Account account = customer.getAccount(acct_idx);
        String account_type = "";

        // Process only savings accounts
        if (account instanceof SavingsAccount) {
          SavingsAccount savings = (SavingsAccount) account;
          savings.accumulateInterest();
        }
        // Skip non-savings accounts
      }
    }
  }
}