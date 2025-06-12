package com.mybank.domain;

/**
 * Exception thrown when a withdrawal amount exceeds the available balance
 * and overdraft protection (if any).
 *
 * @author Sviatoslav Kalinichuk
 */
public class OverDraftAmountException extends Exception {
    private double deficit;

    /**
     * Creates a new overdraft exception with the specified deficit amount and message.
     * @param deficit The amount by which the withdrawal exceeds available funds
     * @param message Detailed error message
     */
    public OverDraftAmountException(double deficit, String message) {
        super(message);
        this.deficit = deficit;
    }

    /**
     * Gets the amount by which the withdrawal exceeds available funds.
     * @return Deficit amount
     */
    public double getDeficit() {
        return deficit;
    }
}
