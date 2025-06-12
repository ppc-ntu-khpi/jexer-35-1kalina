package com.mybank.domain;

/**
 * Represents a savings account with interest accumulation.
 * Does not allow overdraft withdrawals.
 *
 * @author Sviatoslav Kalinichuk
 */
public class SavingsAccount extends Account {
    private double interestRate;

    /**
     * Creates a new savings account with specified balance and interest rate.
     * @param initialBalance Initial account balance
     * @param interestRate Annual interest rate (e.g., 0.05 for 5%)
     */
    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance);
        this.interestRate = interestRate;
    }
    
    /**
     * Accumulates interest on the current balance.
     * Interest is calculated based on the current balance and interest rate.
     */
    public void accumulateInterest() {
        balance += balance * interestRate;
    }

    /**
     * Gets the current interest rate.
     * @return Interest rate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Withdraws money from the account.
     * No overdraft is allowed for savings accounts.
     * @param amount Amount to withdraw
     * @return true if withdrawal was successful
     * @throws OverDraftAmountException if withdrawal amount exceeds available balance
     */
    @Override
    public boolean withdraw(double amount) throws OverDraftAmountException {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            throw new OverDraftAmountException(
                amount - balance,
                "Insufficient funds: withdrawal amount exceeds available balance"
            );
        }
    }
}
