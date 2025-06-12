package com.mybank.domain;

/**
 * Represents a checking account with overdraft protection.
 * Allows withdrawals up to the overdraft limit.
 *
 * @author Sviatoslav Kalinichuk
 */
public class CheckingAccount extends Account {
    private double overdraftAmount;

    /**
     * Creates a new checking account with specified balance and overdraft protection.
     * @param initialBalance Initial account balance
     * @param overdraftAmount Maximum overdraft amount allowed
     */
    public CheckingAccount(double initialBalance, double overdraftAmount) {
        super(initialBalance);
        this.overdraftAmount = overdraftAmount;
    }

    /**
     * Creates a new checking account with specified balance and no overdraft protection.
     * @param initialBalance Initial account balance
     */
    public CheckingAccount(double initialBalance) {
        this(initialBalance, 0);
    }

    /**
     * Gets the current overdraft protection amount.
     * @return Overdraft protection amount
     */
    public double getOverdraftProtection() {
        return overdraftAmount;
    }

    /**
     * Withdraws money from the account, allowing overdraft up to the protection limit.
     * @param amount Amount to withdraw
     * @return true if withdrawal was successful
     * @throws OverDraftAmountException if withdrawal amount exceeds available balance plus overdraft protection
     */
    @Override
    public boolean withdraw(double amount) throws OverDraftAmountException {
        if (balance + overdraftAmount >= amount) {
            balance -= amount;
            return true;
        } else {
            throw new OverDraftAmountException(
                amount - balance - overdraftAmount,
                "Insufficient funds: withdrawal amount exceeds available balance and overdraft protection"
            );
        }
    }
}
