package com.mybank.tui;

import jexer.TAction;
import jexer.TApplication;
import jexer.TField;
import jexer.TText;
import jexer.TWindow;
import jexer.event.TMenuEvent;
import jexer.menu.TMenu;
import com.mybank.data.DataSource;
import java.io.IOException;

/**
 * Main application class for the MyBank Text User Interface.
 * Implements a terminal-based banking interface using the Jexer library.
 *
 * @author Sviatoslav Kalinichuk
 */
public class TUIdemo extends TApplication {

    private static final int ABOUT_APP = 2000;
    private static final int CUST_INFO = 2010;

    public static void main(String[] args) throws Exception {
        TUIdemo tdemo = new TUIdemo();
        (new Thread(tdemo)).start();
    }

    public TUIdemo() throws Exception {
        super(BackendType.XTERM);

        // Initialize and load customer data from the data source
        try {
            // Construct the absolute path to the data file
            String dataFilePath = new java.io.File("").getAbsolutePath() + "/src/main/java/com/mybank/data/test.dat";
            System.out.println("Loading data from: " + dataFilePath); // Log data source location
            DataSource data = new DataSource(dataFilePath);
            data.loadData();
        } catch (IOException e) {
            messageBox("Error", "Failed to load customer data: " + e.getMessage()).show();
            e.printStackTrace(); // Log error details for debugging
        }

        addToolMenu();
        // Initialize File menu with customer information and system options
        TMenu fileMenu = addMenu("&File");
        fileMenu.addItem(CUST_INFO, "&Customer Info");
        fileMenu.addDefaultItem(TMenu.MID_SHELL);
        fileMenu.addSeparator();
        fileMenu.addDefaultItem(TMenu.MID_EXIT);
        // File menu initialization complete

        addWindowMenu();

        // Initialize Help menu with application information
        TMenu helpMenu = addMenu("&Help");
        helpMenu.addItem(ABOUT_APP, "&About...");
        // Help menu initialization complete

        setFocusFollowsMouse(true);
        // Initialize and display the main customer details window
        ShowCustomerDetails();
    }

    @Override
    protected boolean onMenu(TMenuEvent menu) {
        if (menu.getId() == ABOUT_APP) {
            messageBox("About", "\t\t\t\t\t   Just a simple Jexer demo.").show();
            return true;
        }
        if (menu.getId() == CUST_INFO) {
            ShowCustomerDetails();
            return true;
        }
        return super.onMenu(menu);
    }

    /**
     * Creates and displays the customer details window with input field and account information display.
     * Allows users to view customer information by entering a customer number.
     */
    private void ShowCustomerDetails() {
        TWindow custWin = addWindow("Customer Window", 2, 1, 40, 10, TWindow.NOZOOMBOX);
        custWin.newStatusBar("Enter valid customer number and press Show...");

        custWin.addLabel("Enter customer number: ", 2, 2);
        TField custNo = custWin.addField(24, 2, 3, false);
        TText details = custWin.addText("Owner Name: \nAccount Type: \nAccount Balance: ", 2, 4, 38, 8);
        custWin.addButton("&Show", 28, 2, new TAction() {
            @Override
            public void DO() {
                try {
                    int custNum = Integer.parseInt(custNo.getText());
                    if (custNum >= 0 && custNum < com.mybank.domain.Bank.getNumberOfCustomers()) {
                        com.mybank.domain.Customer customer = com.mybank.domain.Bank.getCustomer(custNum);
                        StringBuilder sb = new StringBuilder();
                        sb.append("Owner Name: ").append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("\n");
                        for (int i = 0; i < customer.getNumberOfAccounts(); i++) {
                            com.mybank.domain.Account account = customer.getAccount(i);
                            sb.append("Account ").append(i + 1).append(": ");
                            if (account instanceof com.mybank.domain.SavingsAccount) {
                                sb.append("Savings - Balance: $").append(String.format("%.2f", account.getBalance()));
                                sb.append(" (Interest Rate: ").append(String.format("%.1f", ((com.mybank.domain.SavingsAccount)account).getInterestRate() * 100)).append("%)\n");
                            } else if (account instanceof com.mybank.domain.CheckingAccount) {
                                sb.append("Checking - Balance: $").append(String.format("%.2f", account.getBalance()));
                                sb.append(" (Overdraft Protection: $").append(String.format("%.2f", ((com.mybank.domain.CheckingAccount)account).getOverdraftProtection())).append(")\n");
                            }
                        }
                        details.setText(sb.toString());
                    } else {
                        messageBox("Error", "Invalid customer number!").show();
                    }
                } catch (NumberFormatException e) {
                    messageBox("Error", "You must provide a valid customer number!").show();
                }
            }
        });
    }
} 