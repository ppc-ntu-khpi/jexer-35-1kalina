package com.mybank.domain;

/**
 * Abstract base class representing a bank account.
 * Provides common functionality for all account types.
 *
 * @author Sviatoslav Kalinichuk
 */
public abstract class Account {
    protected double balance;

    /**
     * Creates a new account with the specified initial balance.
     * @param initialBalance Initial account balance
     */
    protected Account(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * Gets the current account balance.
     * @return Current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits money into the account.
     * @param amount Amount to deposit
     * @return true if deposit was successful
     */
    public boolean deposit(double amount) {
        balance += amount;
        return true;
    }

    /**
     * Withdraws money from the account.
     * @param amount Amount to withdraw
     * @return true if withdrawal was successful
     * @throws OverDraftAmountException if withdrawal amount exceeds available balance
     */
    public abstract boolean withdraw(double amount) throws OverDraftAmountException;
}
