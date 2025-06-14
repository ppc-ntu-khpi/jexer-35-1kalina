package testclasses;

import com.mybank.data.DataSource;
import com.mybank.reporting.CustomerReport;
import java.io.IOException;

/**
 *
 * @author Sviatoslav Kalinichuk
 */
public class TestReport {

  private static final String USAGE
    = "USAGE: java com.mybank.test.TestReport <dataFilePath>\nExample: java com.mybank.test.TestReport src/com/mybank/data/test.dat";

  public static void main(String[] args) {

    // Retrieve the dataFilePath command-line argument
    if ( args.length != 1 ) {
      System.out.println(USAGE);
    } else {
      String dataFilePath = args[0];

      try {
	System.out.println("Reading data file: " + dataFilePath);
	// Create the data source and load the Bank data
	DataSource dataSource = new DataSource(dataFilePath);
	dataSource.loadData();

	// Run the customer report
	CustomerReport report = new CustomerReport();
	report.generateReport();

      } catch (IOException ioe) {
	System.out.println("Could not load the data file.");
	System.out.println(ioe.getMessage());
	ioe.printStackTrace(System.err);
      }
    }
  }
}